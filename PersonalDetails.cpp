#include <iostream>
using namespace std;

int main() {
    string name, surname;
    int birthYear;
    cout << "Enter your name: ";
    cin >> name;
    cout << "Enter your surname: ";
    cin >> surname;
    cout << "Enter your birth year: ";
    const int current = 2025;
    cin >> birthYear;
    int age = current - birthYear;

    int pin;
    cout << "Enter your pin: ";
    cin >> pin;
    if (pin==1964) {
    cout << "Name: " << name << "\n";
    cout << "Surname: " << surname << "\n";
    cout << "Age: " << age <<" years old";
    }
    else {
        cout <<"Wrong pin access denied";
    }
    return 0;
}