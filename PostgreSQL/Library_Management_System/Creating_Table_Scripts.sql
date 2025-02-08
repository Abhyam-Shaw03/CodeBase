CREATE TABLE authors (
    author_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(50) NOT NULL,
    dob DATE NOT NULL
);

CREATE TABLE books (
    bookID SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    authorID INT,
	CONSTRAINT fk_author FOREIGN KEY (authorID) REFERENCES authors(author_id) ON DELETE CASCADE,
    genre VARCHAR(100),
    publishedYear INT,
    copiesAvailable INT DEFAULT 1
);

CREATE TABLE members (
    MemberID SERIAL PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    MembershipDate DATE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
	CONSTRAINT chk_email_format CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

CREATE TABLE borrowingRecords (
    RecordID SERIAL PRIMARY KEY,
    Member_ID INT NOT NULL,
	CONSTRAINT fk_members FOREIGN KEY (Member_ID) REFERENCES Members(MemberID) ON DELETE CASCADE,
    Book_ID INT NOT NULL,
	CONSTRAINT fk_books FOREIGN KEY (Book_ID) REFERENCES Books(BookID) ON DELETE CASCADE,
    BorrowDate DATE NOT NULL,
    ReturnDate DATE
);