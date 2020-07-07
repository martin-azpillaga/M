package m.validation;
import static m.validation.Type.*;
import static m.validation.SumType.*;
import static m.validation.ProductType.*;
import static m.validation.PolymorphicType.*;
import static m.validation.ExponentType.*;

public enum Symbol
{
	inputTriggered(proposition),
	inputValue(number),
	inputVector(number2),
	
	timer(number),
	elapsed(number),
	timeout(proposition),
	
	position(number3),
	rotation(number4),
	scale(number3),
	worldPosition(number3),
	worldRotation(number4),
	worldScale(number3),
	parent(entity),
	children(entityList),
	
	mesh(meshType),
	material(materialType),
	
	font(fontType),
	text(textType),
	image(imageType),
	button(proposition),
	numberLabel(number),
	
	viewAngle(number),
	viewDistance(number),
	clearColor(number4),
	perspective(unit),
	
	emission(number4),
	spotAngle(number),
	range(number),
	intensity(number),
	shadows(proposition),
	cookie(imageType),
	
	audioClip(clip),
	volume(number),
	pitch(number),
	loop(unit),
	
	distortion(number),
	echo(number),
	highPass(number),
	lowPass(number),
	reverb(number),
	chorus(number),
	doppler(number),
	
	ip(number4),
	port(number),
	networkStream(unit),
	prediction(unit),
	owner(number),
	
	mass(number),
	velocity(number3),
	acceleration(number3),
	force(number3),
	damping(number),
	angularDamping(number),
	angularVelocity(number3),
	gravityFactor(number),
	
	trigger(unit),
	extents(number3),
	radius(number),
	height(number),
	sideCount(number),
	convexHull(unit),
	geometry(unit),
	restitution(number),
	friction(number),
	
	collisions(entityList),
	
	
	selection(proposition),
	iteration(proposition),
	query(declaration);
	
	public Type type;
	Symbol(Type type) {this.type = type;}
}

enum F
{
	epsilon(constant),
	pi(constant),
	e(constant),
	
	abs(numberNumber),
	sign(numberNumber),
	ceil(numberNumber),
	floor(numberNumber),
	round(numberNumber),
	clamp(numberNumber),
	integerPart(numberNumber),
	fractionalPart(numberNumber),
	inverse(numberNumber),
	reciprocal(numberNumber),
	
	lerp(numberNumber2Number),
	unlerp(numberNumber2Number),
	proportional(numberNumber2Number2Number),
	
	cross(number3Number3Number3),
	dot(number3Number3Number),
	norm(number3Number),
	normalize(number3Number3),
	distance(number3Number3Number),
	reflect(number3Number3Number3),
	refract(number3Number3Number3),
	
	or(propositionPropositionProposition),
	and(propositionProposition),
	not(propositionProposition),
	
	addition(numericNumericNumeric),
	subtraction(numericNumericNumeric),
	multiplication(numericNumberNumeric),
	division(numericNumberNumeric),
	
	equal(anyAny),
	inequal(anyAny),
	
	lower(numberNumberProposition),
	lowerOrEqual(numberNumberProposition),
	greaterOrEqual(numberNumberProposition),
	greater(numberNumberProposition),
	
	size(entityListNumber),
	in(entityEntityListProposition),
	xyz(numberNumberNumberNumber3),
	
	sin(numberNumber),
	cos(numberNumber),
	tan(numberNumber),
	asin(numberNumber),
	acos(numberNumber),
	atan(numberNumber),
	exp(numberNumber),
	log(numberNumber),
	exp10(numberNumber),
	log10(numberNumber),
	pow(numberNumber),
	sqrt(numberNumber),
	random(number2Number),
	
	create(entityEmpty),
	destroy(entityEmpty),
	add(entityEmpty),
	remove(entityEmpty),
	has(entityProposition),
	
	write(anyEmpty),
	writeError(anyEmpty),
	halt(emptyEmpty);
	
	public Type type;
	F(Type t){type=t;}
}
