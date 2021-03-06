#pragma once

// 0 - надо писать, 1 - написал, 0.5 - надо проверить

template <class T>
class CircularBuffer{
private:
	T* _buffer;
	unsigned long _idxIn = 0; // tail
	unsigned long _idxOut = 0; // head
	unsigned long _capacity = 0;
	unsigned long _size = 0;

public:

	//using value_type = T;

	//Конструктор создания пустого буффера
	CircularBuffer(); // 1

	//Деструктор буффера
	~CircularBuffer(); // 1

	//Конструктор копирования
	CircularBuffer(const CircularBuffer& cb); //1

	//Конструирует буфер заданной ёмкости, целиком заполняет его элементом
	CircularBuffer(int capacity, const T& elem); // 1

	//Конструирует буфер заданной ёмкости.
	explicit CircularBuffer(int capacity); // 1

	//Оператор присваивания.
	CircularBuffer& operator=(const CircularBuffer& cb); // 1

	//Доступ по индексу. Не проверяют правильность индекса.
	T& operator[](int i); // 1
	const T& operator[](int i) const; // 1

	//Доступ по индексу. Методы бросают исключение в случае неверного индекса.
	T& at(int i); // 1
	const T& at(int i) const; // 1

	//Ссылка на первый элемент.
	T& front(); // 0.5
	const T& front() const; // 0.5

	//Ссылка на последний элемент.
	T& back(); // 0
	const T& back() const; // 0.5

	//Количество элементов, хранящихся в буфере.
	int size() const; // 1

	//Ёмкость буфера
    int capacity() const; // 1

	//Количество свободных ячеек в буфере.
	int reserve() const; // 1

	//true, если size() == capacity().
	bool full() const; // 1

	//Проверяем, пустой ли буфер (если ёмкость = 0, то false)
	bool empty() const; // 1

	//Добавляет элемент в конец буфера.
	//Если текущий размер буфера равен его ёмкости, то переписывается
	//первый элемент буфера (т.е., буфер закольцован).
	void push_back(const T& item = T()); // 0.5

	//Добавляет новый элемент перед первым элементом буфера.
	//Аналогично push_back, может переписать последний элемент буфера.
	void push_front(const T& item = T()); // 0.5

	//удаляет последний элемент буфера.
	void pop_back(); // 0.5

	//удаляет первый элемент буфера.
	void pop_front(); // 0.5

	//Сдвигает буфер так, что по нулевому индексу окажется элемент
	//с индексом new_begin.
	void rotate(int new_start); // 0
	
	//Линеаризация - сдвинуть кольцевой буфер так, что его первый элемент
	//переместится в начало аллоцированной памяти. Возвращает указатель
    //на первый элемент.
	T* linearize(); // 0

	//Проверяет, является ли буфер линеаризованным.
	bool is_linearized() const; // 0

	void set_capacity(int new_capacity_); // 0

	//Изменяет размер буфера.
	//В случае расширения, новые элементы заполняются элементом item.
	void resize(int new_size, const T& item = T()); // 0

	//Обменивает содержимое буфера с буфером cb.
	void swap(CircularBuffer& cb); // 0

	//Вставляет элемент item по индексу pos. Ёмкость буфера остается неизменной.
	void insert(int pos, const T& item = T()); // 0

	//Удаляет элементы из буфера в интервале [first, last).
	void erase(int first, int last); //0

	//Очищает буфер.
	void clear(); // 0
};

template <class T>
bool operator==(const CircularBuffer<T> &a, const CircularBuffer<T> &b);

template <class T>
bool operator!=(const CircularBuffer<T> &a, const CircularBuffer<T> &b);


#include "circular_buffer.inl"