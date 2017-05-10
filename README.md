Run application as Java Application::

Use chrome Postman to send request to server.

#add shops

POST method:

http://localhost:8080/retail/addshop

Select body raw type and send below json content one by one to add some shops.

{
	"shopName" : "Suruchi Food Corner",
	"shopAddress" : {
		"shopNumber" : "D Y Patil Clg Road",
		"postCode" : "411047"
	}
}


{
	"shopName" : "Tasty Bite Chowpatty",
	"shopAddress" : {
		"shopNumber" : "Sr. no. 296/3/2",
		"postCode" : "411047"
	}
}

{
	"shopName" : "Mad Momos",
	"shopAddress" : {
		"shopNumber" : "Shop. No. 3",
		"postCode" : "411047"
	}
}

#customer query for nearby shop

Calling from The Rose Society::

http://localhost:8080/retail/shopnearby?customerLatitude=18.614861&customerLongitude=73.908058

Should Return::

{
  "shopName": "Suruchi Food Corner",
  "shopLongitude": 73.910401,
  "shopLatitude": 18.615958,
  "shopAddress": {
    "shopNumber": "D Y Patil Clg Road",
    "postCode": "411047"
  }
}

Calling from Park Spring::

Should Return::

http://localhost:8080/retail/shopnearby?customerLatitude=18.610458&customerLongitude=73.910815

{
  "shopName": "Mad Momos",
  "shopLongitude": 73.911734,
  "shopLatitude": 18.612381,
  "shopAddress": {
    "shopNumber": "Shop. No. 3",
    "postCode": "411047"
  }
}
