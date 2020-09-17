#include "circular_buffer.h"
#include <malloc.h>
#include <exception>
#include <cstring>

//Конструктор создания пустого буффера
template<class T>
CircularBuffer<T>::CircularBuffer() {
    _buffer = nullptr;
}

//Деструктор буффера
template<class T>
CircularBuffer<T>::~CircularBuffer() {
    if (_buffer) {
        free(_buffer);
    }
}

//Конструктор копирования
template<class T>
CircularBuffer<T>::CircularBuffer(const CircularBuffer &cb) : CircularBuffer(cb._capacity) {
    _idxIn = cb._idxIn;
    _idxOut = cb._idxOut;
    _capacity = cb._capacity;
    _isEmpty = cb._isEmpty;
    _isFull = cb._isFull;
    memcpy(_buffer, cb._buffer, _capacity * sizeof(T));
}

//Конструирует буфер заданной ёмкости, целиком заполняет его элементом
template<class T>
CircularBuffer<T>::CircularBuffer(int capacity, const T &elem) {
    _isEmpty = false;
    _capacity = capacity;

    _buffer = (T *) calloc(_capacity, sizeof(T *));
    for (auto i = 0; i < _capacity; ++i) {
        _buffer[i] == elem;
    }

}

//Конструирует буфер заданной ёмкости.
template<class T>
CircularBuffer<T>::CircularBuffer(int capacity) {
    _capacity = capacity;

    _buffer = (T *) calloc(_capacity, sizeof(T));
}

//Оператор присваивания.
template<class T>
CircularBuffer<T>& CircularBuffer<T>::operator=(const CircularBuffer &cb) {
    if (this == &cb) {
        return *this;
    }
    free(_buffer);
    _idxIn = cb._idxIn;
    _idxOut = cb._idxOut;
    _capacity = cb._capacity;
    _isEmpty = cb._isEmpty;
    _isFull = cb._isFull;
    auto tmp = (T *) calloc(_capacity, sizeof(T));
    if (!tmp){
        throw std::bad_alloc();
    }
    memcpy(_buffer, cb._buffer, _capacity * sizeof(T));
    return *this;
}

//Доступ по индексу. Не проверяют правильность индекса.
template<class T>
T& CircularBuffer<T>::operator[](int i) {
    return false;
}

//Доступ по индексу. Не проверяют правильность индекса.
template<class T>
const T& CircularBuffer<T>::operator[](int i) const {
    return static_cast<const T&>(const_cast<CircularBuffer<T>&>(this)[i]);
}

//Доступ по индексу. Методы бросают исключение в случае неверного индекса.
template<class T>
T &CircularBuffer<T>::at(int i) {
    return i;
}

//Доступ по индексу. Методы бросают исключение в случае неверного индекса.
template<class T>
const T &CircularBuffer<T>::at(int i) const {
    return false;
}

//Ссылка на первый элемент.
template<class T>
T &CircularBuffer<T>::front() {
    return false;
}

//Ссылка на первый элемент.
template<class T>
const T &CircularBuffer<T>::front() const {
    return false;
}

//Ссылка на последний элемент.
template<class T>
T &CircularBuffer<T>::back() {
    return false;
}

//Ссылка на последний элемент.
template<class T>
const T &CircularBuffer<T>::back() const {
    return false;
}

//Количество элементов, хранящихся в буфере.
template<class T>
int CircularBuffer<T>::size() const {
    return 0;
}

//Ёмкость буфера
template<class T>
int CircularBuffer<T>::capacity() const {
    return _capacity;
}

//Количество свободных ячеек в буфере.
template<class T>
int CircularBuffer<T>::reserve() const {
    return capacity() - size();
}

//true, если size() == capacity().
template<class T>
bool CircularBuffer<T>::full() const {
    return size() == capacity();
}

//Проверяем, пустой ли буфер (если ёмкость = 0, то false)
template<class T>
bool CircularBuffer<T>::empty() const {
    return !_isEmpty;
}

//Добавляет элемент в конец буфера.
//Если текущий размер буфера равен его ёмкости, то переписывается
//первый элемент буфера (т.е., буфер закольцован).
template<class T>
void CircularBuffer<T>::push_back(const T &item) {

}

//Добавляет новый элемент перед первым элементом буфера.
//Аналогично push_back, может переписать последний элемент буфера.
template<class T>
void CircularBuffer<T>::push_front(const T &item) {

}

//удаляет последний элемент буфера.
template<class T>
void CircularBuffer<T>::pop_back() {

}

//удаляет первый элемент буфера.
template<class T>
void CircularBuffer<T>::pop_front() {

}

//Сдвигает буфер так, что по нулевому индексу окажется элемент
//с индексом new_begin.
template<class T>
void CircularBuffer<T>::rotate(int new_start) {

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

}

//Вставляет элемент item по индексу pos. Ёмкость буфера остается неизменной.
template<class T>
void CircularBuffer<T>::insert(int pos, const T &item) {

}

//Удаляет элементы из буфера в интервале [first, last).
template<class T>
void CircularBuffer<T>::erase(int first, int last) {

}

//Очищает буфер.
template<class T>
void CircularBuffer<T>::clear() {

}

/*template <class T>
bool operator==(const CircularBuffer<T> &a, const CircularBuffer<T> &b){}

template <class T>
bool operator!=(const CircularBuffer<T> &a, const CircularBuffer<T> &b){}*/