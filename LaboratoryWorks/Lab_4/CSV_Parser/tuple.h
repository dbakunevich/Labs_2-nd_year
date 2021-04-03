#pragma once

#include <iostream>
#include <tuple>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>

template<typename Ch, typename Tr, typename TupleType, size_t ... I>
void printTuple(std::basic_ostream<Ch, Tr>& os, const TupleType& tuple, std::index_sequence<I...>)
{
    os << "[";
    ((os << (I == 0 ? "" : ", ") << std::get<I>(tuple)), ...);
    os << "]";
}

template<typename Ch, typename Tr, typename ... Args>
auto operator<<(std::basic_ostream<Ch, Tr>& os, std::tuple<Args...> const& t)
-> std::basic_ostream<Ch, Tr>&
{
    printTuple(os, t, std::make_index_sequence<sizeof...(Args)>());
    return os;
}