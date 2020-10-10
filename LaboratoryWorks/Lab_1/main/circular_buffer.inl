#include "circular_buffer.h"
#include <malloc.h>
#include <exception>
#include <cstring>
#include <stdexcept>

//Конструктор создания пустого буффера
template<class T>
CircularBuffer<T>::CircularBuffer() {
    _buffer = nullptr;
}

//Деструктор буффера
template<class T>
CircularBuffer<T>::~CircularBuffer() {
    if (_buffer) {
        delete _buffer;
    }
}

//Конструктор копирования
template<class T>
CircularBuffer<T>::CircularBuffer(const CircularBuffer &cb) : CircularBuffer(cb._capacity) {
    _idxIn = cb._idxIn;
    _idxOut = cb._idxOut;
    _capacity = cb._capacity;
    _size = cb._size;
    memcpy(_buffer, cb._buffer, _capacity * sizeof(T));
    //* this = cb;
}

//Конструирует буфер заданной ёмкости, целиком заполняет его элементом
template<class T>
CircularBuffer<T>::CircularBuffer(int capacity, const T &elem) {
    _capacity = capacity;
    _buffer = new T[_capacity];
    for (int i = 0; i < _capacity; ++i) {
        _buffer[i] = elem;
    }
    _size = _capacity;
    _idxOut = _capacity - 1;
}

//Конструирует буфер заданной ёмкости.
template<class T>
CircularBuffer<T>::CircularBuffer(int capacity) {
    _capacity = capacity;
    _buffer = new T[_capacity];
}

//Оператор присваивания.
template<class T>
CircularBuffer<T>& CircularBuffer<T>::operator=(const CircularBuffer &cb) {
    if (this == &cb) {
        return *this;
    }
    delete []_buffer;
    _buffer = new T[cb._capacity];
    _idxIn = cb._idxIn;
    _idxOut = cb._idxOut;
    _capacity = cb._capacity;
    _size = cb._size;
    memcpy(_buffer, cb._buffer, _capacity * sizeof(T)); //переделать
    return *this;
}

//Доступ по индексу. Не проверяют правильность индекса.
template<class T>
T& CircularBuffer<T>::operator[](int i) {
    return _buffer[i];
}

//Доступ по индексу. Не проверяют правильность индекса.
template<class T>
const T& CircularBuffer<T>::operator[](int i) const {
    return static_cast<const T&>(const_cast<CircularBuffer<T>&>(*this)[i]); ////!!!!!!
}

//Доступ по индексу. Методы бросают исключение в случае неверного индекса.
template<class T>
T &CircularBuffer<T>::at(int i) {
    if (i < 0 || i >= size()) {
        throw std::range_error("bad index");
    }
    return _buffer[i];
}

//Доступ по индексу. Методы бросают исключение в случае неверного индекса.
template<class T>
const T &CircularBuffer<T>::at(int i) const {
    return static_cast<const T&>(const_cast<CircularBuffer<T>&>(*this).at(i));
}

//Ссылка на первый элемент.
template<class T>
T &CircularBuffer<T>::front() {
    return _buffer[_idxIn];
}

//Ссылка на первый элемент.
template<class T>
const T &CircularBuffer<T>::front() const {
    return static_cast<const T&>(const_cast<CircularBuffer<T>&>(*this).front());
}

//Ссылка на последний элемент.
template<class T>
T &CircularBuffer<T>::back() {
    return _buffer[_idxOut];
}

//Ссылка на последний элемент.
template<class T>
const T &CircularBuffer<T>::back() const {
    return static_cast<const T&>(const_cast<CircularBuffer<T>&>(*this).back());
}

//Количество элементов, хранящихся в буфере.
template<class T>
int CircularBuffer<T>::size() const {
    return _size;
}

//Ёмкость буфера
template<class T>
int CircularBuffer<T>::capacity() const {
    return _capacity;
}

//Количество свободных ячеек в буфере.
template<class T>
int CircularBuffer<T>::reserve() const {
    return _capacity - _size;
}

//true, если size() == capacity().
template<class T>
bool CircularBuffer<T>::full() const {
    return size() == capacity();
}

//Проверяем, пустой ли буфер (если ёмкость = 0, то false)
template<class T>
bool CircularBuffer<T>::empty() const {
    if (_capacity == 0){
        return false;
    }
    return true;
}

//Добавляет элемент в конец буфера.
//Если текущий размер буфера равен его ёмкости, то переписывается
//первый элемент буфера (т.е., буфер закольцован).
template<class T>
void CircularBuffer<T>::push_back(const T &item) {
    if (full()){
        _idxOut = _idxIn;
        _idxIn = (_idxIn + 1) % _capacity;
        _buffer[_idxOut] = item;
    }
    else if (_size == 0) {
        _buffer[_idxOut] = item;
        _size++;
    }
    else{
        _idxOut++;
        _buffer[_idxOut] = item;
        _size++;
    }
}

//Добавляет новый элемент перед первым элементом буфера.
//Аналогично push_back, может переписать последний элемент буфера.
// 0 1 2 3 ..
template<class T>
void CircularBuffer<T>::push_front(const T &item) {
    if (full()){
        _idxIn = _idxOut;
        if (_idxOut == 0){
            _idxOut = _capacity - 1;
        }
        else{
            _idxOut--;
        }
        _buffer[_idxIn] = item;
    }
    else if(_size == 0){
        _buffer[_idxIn] = item;
        _size++;
    }
    else{
        if (_idxIn == 0){
            _idxIn = _capacity - 1;
        }
        else{
            _idxIn--;
        }
        _buffer[_idxIn] = item;
        _size++;
    }

}

//delete
// 1 2 3 4 5
// 1 2 3 4

// 6 7 3 4 5
// 6 3 4 5
//удаляет последний элемент буфера.
template<class T>
void CircularBuffer<T>::pop_back() {
    if (_size == 0) {
        throw std::range_error("bad index");
    }
    else if(_size == 1){
        CircularBuffer<T> tmp (_capacity - 1);
        *this = tmp;
    }
    else {
        _capacity--;
        _size--;
        CircularBuffer<T> tmp(_capacity);
        if (full()) {
            for (auto i = 0; i < _idxOut; i++) {
                tmp[i] = (*this)[i];
            }
            for (auto i = _idxOut; i < _capacity; i++) {
                tmp[i] = (*this)[i + 1];
            }
        } else {
            for (auto i = 0; i < _size; i++) {
                tmp[i] = (*this)[i];
            }
        }
        _idxOut--;
        (*this)._buffer = tmp._buffer;
    }

}

//удаляет первый элемент буфера.
template<class T>
void CircularBuffer<T>::pop_front() {

}

//Сдвигает буфер так, что по нулевому индексу окажется элемент
//с индексом new_start.
template<class T>
void CircularBuffer<T>::rotate(int new_start) {
    _idxIn = (_idxIn + new_start) % _capacity;
    _idxOut = (_idxOut + new_start) % _capacity;
}

//Линеаризация - сдвинуть кольцевой буфер так, что его первый элемент
//переместится в начало аллоцированной памяти. Возвращает указатель
//на первый элемент.
template<class T>
T *CircularBuffer<T>::linearize() {
    return nullptr;
}

//Проверяет, является ли буфер линеаризованным.
template<class T>
bool CircularBuffer<T>::is_linearized() const {
    return false;
}

//Проверяет, является ли буфер линеаризованным.
template<class T>
void CircularBuffer<T>::set_capacity(int new_capacity_) {

}

//Изменяет размер буфера.
//В случае расширения, новые элементы заполняются элементом item.
template<class T>
void CircularBuffer<T>::resize(int new_size, const T &item) {

}

//Обменивает содержимое буфера с буфером cb.
template<class T>
void CircularBuffer<T>::swap(CircularBuffer &cb) {
    CircularBuffer<T> tmp = cb;
    cb = *this;
    *this = tmp;
}

//Вставляет элемент item по индексу pos. Ёмкость буфера остается неизменной.
template<class T>
void CircularBuffer<T>::insert(int pos, const T &item) {
    if (_capacity <= pos || pos < 0){
        throw std::range_error("bad index");
    }
    _buffer[pos] = item;
}

//Удаляет элементы из буфера в интервале [first, last).
template<class T>
void CircularBuffer<T>::erase(int first, int last) {

}

//Очищает буфер.
template<class T>
void CircularBuffer<T>::clear() {
    delete [] _buffer;
    _idxIn = _idxOut = 0;
    _capacity = _size = 0;
    _buffer = nullptr;
}

template <class T>
bool operator==(const CircularBuffer<T> &a, const CircularBuffer<T> &b){
    if (a.size() != b.size())
        return false;
    for (int i = 0; i < a.size(); ++i)
        if (a[i] != b[i])
            return false;

    return true;
}

template <class T>
bool operator!=(const CircularBuffer<T> &a, const CircularBuffer<T> &b){
    return !(a == b);
}