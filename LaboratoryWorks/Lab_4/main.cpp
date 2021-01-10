#include "CSV_Parser/csv_parser.h"
#include "CSV_Parser/tuple.h"

int main() {
    std::ifstream infile("/home/dmitry/CLionProjects/OOP/ooop-19208/LaboratoryWorks/Lab_4/test.txt");
    std::ofstream outfile("/home/dmitry/CLionProjects/OOP/ooop-19208/LaboratoryWorks/Lab_4/test.cvs");
    CSVParser<int, std::string, std::string, std::string, double> parser(infile);
    for (const auto &rs : parser) {
        outfile << rs << std::endl;
    }
}
