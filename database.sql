create database PlantShop
use PlantShop
create table Accounts(
    accID int identity(1,1)primary key,
    email varchar(30)  unique,
    password varchar(30),
    fullname varchar(30),
    phone varchar(12),
    status int check(status =1 or status=0),-- 1:active; 0:inactive
    role int check(role=1 or role=0)    --:admin, 0:user
)
create table Categories(
      CateID int identity(1,1) primary key,
      CateName varchar(30)
)
create table Plants(
      PID int identity(1,1) primary key,
      PName varchar(30),
      price int check(price>=0),
      imgPath varchar(50),
      description text,
      status int,    --1:active, 0:inactive
      CateID int foreign key references Categories(CateID)
)
create table Orders(
    OrderID int identity(1,1) primary key,
    OrdDate date,
    shipdate date,
    status int check(status =1 or status=2 or status=3),--1:processing, 2: completed, 3: cancel
    AccID int foreign key references Accounts(AccID)
)
create table OrderDetails(
    DetailId int identity(1,1) primary key,
    OrderID int foreign key references Orders(OrderID),
    PID int foreign key references Plants(PID),
    quantity int check(quantity>=1)
)
INSERT INTO Categories (CateName)
VALUES ('Fruit'), ('Flower');

INSERT INTO Plants (PName, price, imgPath, description, status, CateID)
VALUES
('Apple', 10, 'apple.jpg', 'A red delicious apple', 1, (SELECT CateID FROM Categories WHERE CateName = 'Fruit')),
('Banana', 5, 'banana.jpg', 'A yellow sweet banana', 1, (SELECT CateID FROM Categories WHERE CateName = 'Fruit')),
('Cherry', 7, 'cherry.jpg', 'A red sweet cherry', 1, (SELECT CateID FROM Categories WHERE CateName = 'Fruit')),
('Grapes', 8, 'grapes.jpg', 'A green juicy grapes', 1, (SELECT CateID FROM Categories WHERE CateName = 'Fruit')),
('Kiwi', 9, 'kiwi.jpg', 'A green sweet kiwi', 1, (SELECT CateID FROM Categories WHERE CateName = 'Fruit')),
('Rose', 15, 'rose.jpg', 'A beautiful red rose', 1, (SELECT CateID FROM Categories WHERE CateName = 'Flower')),
('Lily', 20, 'lily.jpg', 'A white delicate lily', 1, (SELECT CateID FROM Categories WHERE CateName = 'Flower')),
('Jasmine', 25, 'jasmine.jpg', 'A white fragrant jasmine', 1, (SELECT CateID FROM Categories WHERE CateName = 'Flower')),
('Daisy', 18, 'daisy.jpg', 'A white cheerful daisy', 1, (SELECT CateID FROM Categories WHERE CateName = 'Flower')),
('Tulip', 22, 'tulip.jpg', 'A red beautiful tulip', 1, (SELECT CateID FROM Categories WHERE CateName = 'Flower'));

