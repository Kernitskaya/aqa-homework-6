#language:ru

Функционал: Перевод с карты на карту
  Предыстория:
    Дано на каждой из карт по 10000 рублей

  Сценарий: Проверка перевода с карты на карту
    Если запустить сервис перевода средств между своими картами
    И авторизоваться с логином "vasya" и паролем "qwerty123"
    И ввести код верификации "12345"
    И перевести "500" рублей на первую карту, с карты с номером "5559 0000 0000 0002"
    Тогда баланс на первой карте из списка на главной странице должен стать "10500" рублей
