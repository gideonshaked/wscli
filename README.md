# wscli

Wscli is a program to display word searches in the CLI. It can use several preexisting word lists, or you can provide your own. Enjoy!

![Wscli Example](https://github.com/The-Kid-Gid/wscli/blob/master/img/example.svg)

## Requirements

- [Java](https://www.java.com/en/download/)

## Installation

Run the install script.

```shell
curl https://raw.githubusercontent.com/The-Kid-Gid/wscli/master/install.sh | bash
```

This will install wscli to `~/.local/bin`. It will also add a bash alias so that you can just use the command `wscli` install of `java <path to wscli.java>`.

## Usage

```
❯ wscli --help
usage: wscli [-h] (-w <default words list> | -f <path to valid words file>)

Display a word search to the terminal.

arguments:
  -h, --help  Show this help message and exit.
  -w, --words Name of one of several default words lists that come with wscli.
                          Valid options: (animals, cars, countries, names, presidents)
  -f, --file  Path to valid custom words file. Must be one word per line with maximum 20 characters.
```

## Examples

```shell
❯ wscli -w animals
----------------------------------------------------------
f  c  p  k  m  u  w  d  b  a  l  d     e  a  g  l  e  t  z  
r  h  v  d  z  a  s  p  m  u  t  a  r  a  n  t  u  l  a  y  
z  g  z  q  h  a  q  q  f  z  j  z  k  t  k  l  u  q  u  u  
p  p  o  l  a  r     b  e  a  r  t  r  v  s  r  u  i  r  d  
z  b  s  i  k  d  f  g  j  v  h  y  h  v  q  a  s  l  c  b  
x  y  e  a  x  r  h  v  g  b  j  a  l  k  m  r  w  q  h  f  
y  d  s  m  g  i  l  a     m  o  n  s  t  e  r  k  f  b  x  
r  k  y  q  b  b  j  l  o  v  b  y  a  f  b  a  m  a  m  s  
m  b  u  w  a  q  y  r  s  t  f  c  u  l  x  q  r  a  m  b  
p  k  f  h  l  q  c  e  t  z  o  e  a  k  g  c  h  k  z  w  
k  i  t  e  o  a  z  x  g  x  v  u  y  w  t  h  o  m  a  q  
c  y  z  r  b  f  b  b  f  h  d  r  c  i  k  e  f  w  u  x  
i  k  a  d  e  y  p  f  u  h  u  m  c  a  r  r  l  d  w  a  
h  h  a  c  w  s  j  m  v  i  h     s  z  n  k  d  p  l  o  
o  b  o  b  o  l  i  n  k  s  f  r  n  s  q  f  w  h  x  d  
j  e  f  t  u  l  w  b  j  o  t  l  e  x  z  q  b  a  k  x  
h  o  j  m  f  k  d  w  x  p  m  j  w  u  g  r  j  l  w  g  
l  v  p  v  p  t  j  h  z  m  b  z  t  v  t  d  s  s  r  g  
l  x  q  q  u  s  g  h  g  z  l  a  n  d  f  o  w  l  i  p  
f  r  b  h  g  j  z  k  y  o  e  r  k  s  c  u  j  l  i  w  
----------------------------------------------------------
Words: newt, kite, gila monster, asp, bald eagle, tarantula, polar bear, bobolink, landfowl, toucan, orca, arctic fox
```

```shell
❯ wscli -w cars
----------------------------------------------------------
h  r  f  d  b  h  r  m  p  j  w  b  k  t  e  e  z  f  d  o  
a  o  w  a  r  k  g  p  d  s  y  k  m  e  d  t  d  r  r  b  
u  e  a  l  u  b  a  f  s  l  b  x  a  g  t  o  i  g  m  b  
c  o  a  a  q  c  t  p  t  x  d  y  z  f  j  l  r  x  d  v  
o  g  l  j  c  j  b  a  u  d  i  c  d  r  v  d  r  w  t  q  
v  e  s  e  p  y  h  u  j  b  b  g  a  d  i  s  x  j  c  z  
h  m  q  t  e  m  y  l  b  a  k  q  e  y  o  m  v  x  a  l  
u  w  r  j  f  p  r  l  z  t  y  v  c  w  c  o  o  z  m  i  
m  h  m  a  l  w  d  e  i  k  p  a  e  q  b  b  l  z  l  l  
m  w  f  i  s  m  y  m  i  b  q  x  d  x  k  i  k  u  h  g  
e  t  f  f  b  f  h  a  s  e  l  p  m  s  w  l  s  o  f  q  
r  p  o  o  c  p  u  x  f  u  f  e  w  i  a  e  w  v  a  z  
g  b  l  y  r  j  t  s  d  g  r  q  i  d  y  f  a  r  f  f  
f  u  t  o  o  d  x  i  i  c  e  s  t  d  s  f  g  q  i  s  
m  i  g  i  b  t  j  t  u  e  u  a  y  u  m  b  e  j  i  f  
h  c  d  k  l  o  a  r  p  z  y  s  f  o  r  f  n  f  t  b  
r  k  e  x  m  c  y  f  u  a  s  p  i  z  k  t  d  p  v  s  
c  p  g  f  i  p  y  f  j  l  x  e  x  k  l  f  l  s  a  x  
m  w  v  i  d  s  t  f  q  d  g  q  c  d  o  y  d  l  p  s  
z  d  y  g  l  x  s  p  o  r  i  j  f  z  l  r  e  z  s  p  
----------------------------------------------------------
Words: mazda, hummer, volkswagen, oldsmobile, buick, gem, audi, ford, toyota, isuzu, mercury, kia
```