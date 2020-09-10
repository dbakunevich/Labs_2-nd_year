#pragma once

template <class T>
class CircularBuffer {
private:
	T* buffer;
public:
	using value_type = T;

	CircularBuffer();
	~CircularBuffer();

	CircularBuffer(const CircularBuffer& cb);

	//Конструирует буфер заданной ёмкости, целиком заполняет его элементом
	CircularBuffer(int capacity, const T& elem);

	//Конструирует буфер заданной ёмкости.
	explicit CircularBuffer(int capacity);

	//Оператор присваивания.
	CircularBuffer& operator=(const CircularBuffer& cb);

	//Доступ по индексу. Не проверяют правильность индекса.
	T& operator[](int i);
	const T& operator[](int i) const;

	//Доступ по индексу. Методы бросают исключение в случае неверного индекса.
	T& at(int i);
	const T& at(int i) const;

	//Ссылка на первый элемент
	T& front();
	const T& front() const;

	//Ссылка на последний элемент.
	T& back();
	const T& back() const;

	//Количество элементов, хранящихся в буфере.
	int size() const;

	//ёмкость буфера
	int capacity() const;

	//Количество свободных ячеек в буфере.
	int reserve() const;

	//true, если size() == capacity().
	bool full() const;

	//Проверяем, пустой ли буфер (если ёмкость = 0, то false)
	bool empty() const;

	//Добавляет элемент в конец буфера.
	//Если текущий размер буфера равен его ёмкости, то переписывается
	//первый элемент буфера (т.е., буфер закольцован).
	void push_back(const T& item = T());

	//Добавляет новый элемент перед первым элементом буфера.
	//Аналогично push_back, может переписать последний элемент буфера.
	void push_front(const T& item = T());

	//удаляет последний элемент буфера.
	void pop_back();

	//удаляет первый элемент буфера.
	void pop_front();

	//Сдвигает буфер так, что по нулевому индексу окажется элемент
	//с индексом new_begin.
	void rotate(int new_start);
	
	//Линеаризация - сдвинуть кольцевой буфер так, что его первый элемент
	//переместится в начало аллоцированной памяти. Возвращает указатель
    //на первый элемент.
	T* linearize();

	//Проверяет, является ли буфер линеаризованным.
	bool is_linearized() const;

	void set_capacity(int new_capacity_);

	//Изменяет размер буфера.
	//В случае расширения, новые элементы заполняются элементом item.
	void resize(int new_size, const T& item = T());

	//Обменивает содержимое буфера с буфером cb.
	void swap(CircularBuffer& cb);

	//Вставляет элемент item по индексу pos. Ёмкость буфера остается неизменной.
	void insert(int pos, const T& item = T());

	//Удаляет элементы из буфера в интервале [first, last).
	void erase(int first, int last);

	//Очищает буфер.
	void clear();									
};

template <class T>
bool operator==(const CircularBuffer<T> &a, const CircularBuffer<T> &b);

template <class T>
bool operator!=(const CircularBuffer<T> &a, const CircularBuffer<T> &b);
