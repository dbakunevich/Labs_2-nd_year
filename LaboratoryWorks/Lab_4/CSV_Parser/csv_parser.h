#pragma once

#include <iostream>
#include "iter.h"

template<typename ... Args>
class CSVParser
{
private:
    std::istream& istream;
    char lineDelim;
    char valueDelim;
    char escapeChar;

public:
    explicit CSVParser(std::istream& is, char lineDelimiter = '\n', char valueDelimiter = ',', char escape = '\"')
            : istream(is),
              lineDelim(lineDelimiter),
              valueDelim(valueDelimiter),
              escapeChar(escape) {}

    CSVIterator<Args...> begin()
    {
        CSVIterator<Args...> it(&istream, lineDelim, valueDelim, escapeChar);
        return it;
    }

    CSVIterator<Args...> end()
    {
        return CSVIterator<Args...>();
    }
};