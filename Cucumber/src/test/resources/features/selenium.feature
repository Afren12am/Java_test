# language: ru

@all
Функция: Добавление товаров

	Предыстория:
		* Открытие вкладки "Food" и установка таймаутов

	@correct
	Сценарий: Добавление овоща с пометкой "Экзотический"
		* Нажатие на кнопку "Добавить" и проверка на появление окна добавления товара
		* Заполняется поле Наименование значением "Огyrчик1!"
		* Выбирается в поле Тип, значение "VEGETABLE"
		* Проставление галочки в поле "Экзотический"
		* Отображение овоща в таблице
		* Закрытие вкладки браузера

	@correct
	Сценарий: Добавление фрукта без пометки "Экзотический"
		* Нажатие на кнопку "Добавить" и проверка на появление окна добавления товара
		* Заполняется поле Наименование значением "Персик3?"
		* Выбирается в поле Тип, значение "FRUIT"
		* Отображение фрукта в таблице
		* Закрытие вкладки браузера