# congestion-tax-management

I have created REST API using spring boot framework, JPA and H2 in-memory database.

You can clone the project and run the application using Eclipse or IntelliJ.

The sample data files are kept in the resource folder. Once you run the application, the data is inserted into the database.

File List:
TaxRule.csv
TollDatafromTraffik.csv

Please use the below URL to access the API method. In the URL, this "/Gothenburg/1232" part is the parameter. The first parameter is city name and the second parameter is vehicle number.

http://localhost:8081/api/1.0/vehicle/Gothenburg/1232

Then it will provide the congestion tax details in the below format.

{
    "vehicleNumber": 1591,
    "totalTaxAmount": 60,
    "entryAndExitDetailsList": [
        {
            "entryNo": 1,
            "entryOrExitTime": "2013-01-14T21:00:00",
            "amount": 0
        },
        {
            "entryNo": 2,
            "entryOrExitTime": "2013-02-08T06:22:00",
            "amount": 8
        },
        {
            "entryNo": 3,
            "entryOrExitTime": "2013-02-08T07:42:00",
            "amount": 18
        },
        {
            "entryNo": 4,
            "entryOrExitTime": "2013-02-08T08:42:00",
            "amount": 8
        },
        {
            "entryNo": 5,
            "entryOrExitTime": "2013-02-08T12:30:00",
            "amount": 8
        },
        {
            "entryNo": 6,
            "entryOrExitTime": "2013-02-08T14:00:00",
            "amount": 8
        },
        {
            "entryNo": 7,
            "entryOrExitTime": "2013-02-08T15:47:00",
            "amount": 18
        }
    ]
}
