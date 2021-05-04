def extendedGDC(a, b):
	if a == 0:
		return b, 0, 1

	gcd, x1, y1 = extendedGDC(b % a, a)

	x = y1 - (b // a) * x1
	y = x1

	return gcd, x, y

def diofanteEquation(a, b, c):
	g, x, y = extendedGDC(a, b)
	if c % g != 0:
		print ("No solutions")
	else:
		x *= c // g
		y *= c // g
		print("x = ", x, "+", b // g, "n")
		print("y = ", y, "-", a // g, "n")

a, b = 35, 15
g, x, y = extendedGDC(a, b)

print(x,".",a," + ",y,".",b," = ",g)

c = 10
print(a,"x","+",b,"y","=",c)
diofanteEquation(a,b,c)
