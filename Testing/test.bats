strace -f -e trace=write -e "signal=!all" -o trace -s 1999 java -cp "ls.jar:." Client.java

cat trace | sed '/+++/d' | sed '/write([237],/d' > out