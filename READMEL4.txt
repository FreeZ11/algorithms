aby program poprawnie się skompilował należy użyć poniższej komendy ze względu że jeden z pakietów nie jest już wspierany przez jave 11 musimy kompilować plik jako java 8
javac -source 1.8 Main.java


aby poprawnie uruchomić program należy wpisać:
java Main --type bst nazwaplikuimput.txt > res.txt

możliwe typy to bst/rbt/hmap
ważne by plik z imputem jak i pliki load były w tym samym folderze co kod.
 przykład struktury pliku zawierającego dane wejściowe

17
max
insert_aaa
insert_a
insert_b
insert_ab
inorder
delete_a
delete_b
max
load_sample.txt
find_three
delete_three
find_three
find_Three
delete_Three
find_Three
min

nie różni się on wiele od sposobu wykładowcy jedynie co bardzo ważne zamias sapcji jest _.
