--:THE CASE QUERIES BEGIN:--

--1.1) List all books and their authors.

SELECT b.title AS "Book Title", a.name AS "Author Name"
FROM authors a
JOIN books b ON a.author_id = b.authorID;

--1.2) Show all members who joined in the last year.

SELECT name 
FROM members 
WHERE EXTRACT(YEAR FROM "membershipdate") = 2024;

--1.3) Display all books in the “Fiction” genre.

SELECT b.title AS "Book Name"
FROM books b
WHERE b.genre = 'Fiction';

--2.1) Find the names of members and the titles of books they have borrowed.

SELECT m.Name AS "Member Name", b.title AS "Book Title"
FROM borrowingRecords br
JOIN members m ON br.Member_ID = m.MemberID
JOIN books b ON br.Book_ID = b.bookID;

--2.2) List books along with their authors’ names and countries.

SELECT b.title AS "Book Name", a.name AS "AUTHOR's NAME", a.country AS "AUTHOR's COUNTRY"
FROM books b
JOIN authors a ON b.authorid = a.author_id;

--3.1) Count how many books are available for each genre.

SELECT b.genre AS "Book Genre", COUNT(copiesAvailable) AS "Book Count"
FROM books b
GROUP BY genre;

--3.2) Find the most borrowed book.

SELECT b.title AS "Most Borrowed Book", b.genre AS "Book Genre", COUNT(br.Book_ID) AS "Borrow Count"
FROM borrowingRecords br
JOIN books b ON br.Book_ID = b.bookID
GROUP BY b.title, b.genre
ORDER BY COUNT(br.Book_ID) DESC
LIMIT 1;

--5.1) Update the CopiesAvailable when a book is borrowed or returned.

CREATE OR REPLACE FUNCTION manage_copies_available()
RETURNS TRIGGER AS $$
BEGIN
    -- When a book is borrowed (INSERT)
    IF TG_OP = 'INSERT' THEN  
        UPDATE books
        SET CopiesAvailable = CopiesAvailable - 1
        WHERE bookID = NEW.Book_ID
        AND CopiesAvailable > 0;  -- Prevents borrowing if no copies are available

    -- When a book is returned (UPDATE: ReturnDate changed from NULL to NOT NULL)
    ELSIF TG_OP = 'UPDATE' AND OLD.ReturnDate IS NULL AND NEW.ReturnDate IS NOT NULL THEN  
        UPDATE books
        SET CopiesAvailable = LEAST(CopiesAvailable + 1, TotalCopies)  --Prevents increase of copiesavailable more than total copies in stock
        WHERE bookID = NEW.Book_ID;

    -- When a book is deleted from borrowingRecords (DELETE: Record removed)
    ELSIF TG_OP = 'DELETE' THEN  
        UPDATE books
        SET CopiesAvailable = LEAST(CopiesAvailable + 1, TotalCopies) --Prevents increase of copiesavailable more than total copies in stock
        WHERE bookID = OLD.Book_ID;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--trigger created below:

CREATE TRIGGER manage_book_copies
AFTER INSERT OR UPDATE OR DELETE 
ON borrowingRecords
FOR EACH ROW
EXECUTE FUNCTION manage_copies_available();


--5.2) Delete a member’s record after their membership expires.

DELETE FROM members WHERE MembershipDate < CURRENT_DATE - INTERVAL '1 year';

--4.1) List the books written by authors who were born before 1950.

SELECT b.title AS "Book Name"
FROM books b
WHERE b.authorID IN (
    SELECT a.author_id
    FROM authors a
    WHERE EXTRACT(YEAR FROM a.dob) < 1950
);

--4.2) Find members who borrowed books published after 2010.

SELECT m.name AS "Member Name"
FROM members m
JOIN borrowingRecords br ON m.MemberID = br.Member_ID
JOIN books b ON br.Book_ID = b.bookID
WHERE b.publishedYear > 2010;

--6) IMPORTANT : Find members who have borrowed more than 2 books in the last 6 months.

SELECT m.name AS "Member Name"
FROM members m
JOIN borrowingRecords br ON m.MemberID = br.Member_ID
WHERE br.BorrowDate >= CURRENT_DATE - INTERVAL '6 months'
GROUP BY m.name
HAVING COUNT(br.Book_ID) > 2;