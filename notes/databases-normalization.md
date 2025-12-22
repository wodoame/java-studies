First Normal Form violation:
1. No primary key
2. Mixing data types
3. Using row order to convey meaning (there should rather be another column that can be used to determine the order)
4. Repeating groups (You shouldn't store a list of items in one column. It's better to use another table)
`Second Normal Form`: Each non-key attribute must depend on the `entire` primary key
`Third Normal Form`: A `non-key` attribute must not depend on another non-key attribute or Every non-key attribute in a table
should depend on the key, the whole key, and nothing but the key. 
`Boyce-Codd Normal Form` (stricter 3NF): An attribute must not depend on another non-key attribute or Every non-key attribute in a table
should depend on the key, the whole key, and nothing but the key. 
`Fourth Normal Form`: Multivalued dependencies in a table must be Multivalued dependencies on the key
`Fifth Normal Form`: The table (which must be in 4NF) cannot be describable as the logical result of joining some other tables together.
If the information can be represented by creating other tables then the data is not in 5NF.

