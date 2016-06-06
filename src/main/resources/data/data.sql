--password : 12345
INSERT INTO Account (id, city, district, email, first_name, last_name, password, phone, user_role, created_date, last_modified_date, version) VALUES ('1', 'California', 'minnesota', 'omnifood@gmail.com', 'Rohat', 'Sahin', '8cb2237d0679ca88db6464eac60da96345513964', '09008006092', 'User', NOW(), NULL, 0);

INSERT INTO Product (id, guid, thumb, title, body, featured )VALUES('1', 'proguid1', 'thumb','title', 'body', 'true');
INSERT INTO Product (id, guid, thumb, title, body, featured )VALUES('2', 'proguid2', 'thumb','title', 'body', 'true');
INSERT INTO Product (id, guid, thumb, title, body, featured )VALUES('3', 'proguid3', 'thumb','title', 'body', 'false');
INSERT INTO Product (id, guid, thumb, title, body, featured )VALUES('4', 'proguid4', 'thumb','title', 'body', 'true');
INSERT INTO Product (id, guid, thumb, title, body, featured )VALUES('5', 'proguid5', 'thumb','title', 'body', 'false');

INSERT INTO Restaurant (restaurant_id,name, address, city, description, district, phone_number, url) VALUES ('2', 'restaurant_name', '50 Market Street, San Francisco, California 94103, United States', 'Istanbul', 'Test Restaurant', 'Bakirkoy', '23232343432', 'http://mado.com.tr/storage/images/1441953857_soc.jpg');

INSERT INTO Category(id, guid, thumb, title, description, url, featured, icon)VALUES('1', 'catguid1','thumb','anayemek','description','url','false','icon');
INSERT INTO Category(id, guid, thumb, title, description, url, featured, icon)VALUES('2', 'catguid2','thumb','meze','description','url','false','icon');
INSERT INTO Category(id, guid, thumb, title, description, url, featured, icon)VALUES('3', 'catguid3','thumb','salata','description','url','true','icon');
INSERT INTO Category(id, guid, thumb, title, description, url, featured, icon)VALUES('4', 'catguid4','thumb','tatlı','description','url','false','icon');
INSERT INTO Category(id, guid, thumb, title, description, url, featured, icon)VALUES('5', 'catguid5','thumb','yemegin_yanında','description','url','true','icon');

INSERT INTO restaurant_category(restaurant_id, category_id)VALUES('2', '1');
INSERT INTO restaurant_category(restaurant_id, category_id)VALUES('2', '2');
INSERT INTO restaurant_category(restaurant_id, category_id)VALUES('2', '3');
INSERT INTO restaurant_category(restaurant_id, category_id)VALUES('2', '4');
INSERT INTO restaurant_category(restaurant_id, category_id)VALUES('2', '5');

INSERT INTO Location(id,address_name,title,city,district,address,address_info,phone_number)VALUES('1','addressName','title','city','district','address','addressInfo','phoneNumber');

INSERT INTO account_locations(account_id,location_id)VALUES('1','1');

INSERT INTO Account_Order(id, account_id, price, location_id)VALUES('1','1','100','1');
INSERT INTO Account_Order(id, account_id, price, location_id)VALUES('2','1','100','1');
INSERT INTO Account_Order(id, account_id, price, location_id)VALUES('3','1','100','1');

INSERT INTO order_products(order_id,products_id)VALUES('1','1');
INSERT INTO order_products(order_id,products_id)VALUES('1','2');
INSERT INTO order_products(order_id,products_id)VALUES('1','3');
INSERT INTO order_products(order_id,products_id)VALUES('2','4');
INSERT INTO order_products(order_id,products_id)VALUES('2','5');
INSERT INTO order_products(order_id,products_id)VALUES('3','5');

INSERT INTO product_category (category_id, product_id)VALUES('1','1');
INSERT INTO product_category (category_id, product_id)VALUES('1','2');
INSERT INTO product_category (category_id, product_id)VALUES('2','3');
INSERT INTO product_category (category_id, product_id)VALUES('2','4');
INSERT INTO product_category (category_id, product_id)VALUES('3','5');