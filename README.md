# Book Review API #

This API allows you to Review a book where, you *may* leave Description of your Review, along with a mandatory rating 
between 0-10 Inclusive.

The API is available at `localhost:8080/api/v1/bookie`

## Endpoints ##

### All Reviews ###

GET `/`

Returns all Stored Reviews within the Database.

### Submit a Review ###

POST `/`, 

Along with Submitted JSON Content-Type including Review in Raw Data Format.

Allows you to submit a new Review.

The request body needs to be in JSON format and include the following properties:

 - `bookTitle` - Integer - Required
 - `bookReview` - String - Optional
 - `bookRating` - Integer - Required

Example
```
POST '/'

{
  "bookTitle": "Harry Potter and the Sorcerer's Stone",
  "bookReview": "7"
}
```
The response body will contain the Status Code along with *No* Message.

### Update a Review ###

Put `/studentID?{key being Updated = Updated Value}` ID-Type: Long

Allows you to Update a review and change only the Review and/or the Rating.

### Deleting a Review ###

DELETE `/studentId`

Allows you to Delete a review with Student ID: {`studentId`}

