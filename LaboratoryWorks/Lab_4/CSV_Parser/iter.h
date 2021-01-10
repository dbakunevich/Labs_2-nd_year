#pragma once

#include <vector>
#include <string>
#include <tuple>
#include <sstream>

template<typename ... Args>
class CSVIterator
{
private:
    std::istream* inputStream;
    std::tuple<Args...> curTuple;
    char lineDelim;
    char valueDelim;
    char escapeChar;

    enum class CSVParsingState
    {
        UnescapedField,
        EscapedField,
        EscapedEscape
    };

    std::vector<std::string> SplitIntoStrings(const std::string& s)
    {
        CSVParsingState state = CSVParsingState::UnescapedField;

        std::vector<std::string> vec;
        std::string curString;

        for (char c : s)
        {
            switch (state)
            {
                case CSVParsingState::UnescapedField:
                    if (c == valueDelim)
                    {
                        vec.emplace_back(std::move(curString));
                        curString.clear();
                    }
                    else if (c == escapeChar)
                    {
                        state = CSVParsingState::EscapedField;
                    }
                    else
                    {
                        curString.push_back(c);
                    }
                    break;
                case CSVParsingState::EscapedField:
                    if (c == escapeChar)
                    {
                        state = CSVParsingState::EscapedEscape;
                    }
                    else
                    {
                        curString.push_back(c);
                    }
                    break;
                case CSVParsingState::EscapedEscape:
                    if (c == escapeChar)
                    {
                        curString.push_back(c);
                        state = CSVParsingState::EscapedField;
                    }
                    else if (c == valueDelim)
                    {
                        vec.emplace_back(std::move(curString));
                        curString.clear();
                        state = CSVParsingState::UnescapedField;
                    }
                    else
                    {
                        state = CSVParsingState::EscapedField;
                    }
                    break;
            }
        }
        vec.emplace_back(std::move(curString));
        return vec;
    }

    template<typename T>
    void ParseT(const std::string& s, T& t)
    {
        std::istringstream stream(s);
        if ((stream >> t).fail() || !(stream >> std::ws).eof())
        {
            throw std::runtime_error("Error, 90, Type Error");
        }
    }

    void ParseT(const std::string& s, std::string& t)
    {
        t = s;
    }

    template<size_t ... I>
    void ReadIntoTuple(const std::vector<std::string>& valuesVec, std::index_sequence<I...>)
    {
        ((ParseT(valuesVec[I], std::get<I>(curTuple))), ...);
    }

public:
    explicit CSVIterator(std::istream* istr = nullptr,
                         char lineDelim = '\n', char valueDelim = ',', char escape = '\"') : inputStream(istr),
                                                                                             lineDelim(lineDelim),
                                                                                             valueDelim(valueDelim),
                                                                                             escapeChar(escape)
    {
        ++(*this);
    }

    CSVIterator<Args...>& operator++()
    {
        if (inputStream == nullptr) return *this;

        std::string s;
        std::getline(*inputStream, s, lineDelim);
        if (s.empty() && inputStream->eof())
        {
            inputStream = nullptr;
            return *this;
        }

        auto valuesVec = SplitIntoStrings(s);
        if (sizeof...(Args) != valuesVec.size()){
            throw std::runtime_error("Error, 128, Type Error");
        }

        ReadIntoTuple(valuesVec, std::index_sequence_for<Args...>());

        return *this;
    }

    std::tuple<Args...>& operator*()
    {
        return curTuple;
    }

    bool operator!=(const CSVIterator<Args...>& other)
    {
        return inputStream != other.inputStream;
    }
};