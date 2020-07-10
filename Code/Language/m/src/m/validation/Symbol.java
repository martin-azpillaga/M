package m.validation;
import static m.validation.ExponentType.anyAny;
import static m.validation.ExponentType.anyEmpty;
import static m.validation.ExponentType.constant;
import static m.validation.ExponentType.emptyEmpty;
import static m.validation.ExponentType.entityEmpty;
import static m.validation.ExponentType.entityEntityListProposition;
import static m.validation.ExponentType.entityListNumber;
import static m.validation.ExponentType.entityProposition;
import static m.validation.ExponentType.number2Number;
import static m.validation.ExponentType.number3Number;
import static m.validation.ExponentType.number3Number3;
import static m.validation.ExponentType.number3Number3Number;
import static m.validation.ExponentType.number3Number3Number3;
import static m.validation.ExponentType.numberNumber;
import static m.validation.ExponentType.numberNumber2Number;
import static m.validation.ExponentType.numberNumber2Number2Number;
import static m.validation.ExponentType.numberNumberNumberNumber3;
import static m.validation.ExponentType.numberNumberProposition;
import static m.validation.ExponentType.numericNumberNumeric;
import static m.validation.ExponentType.numericNumericNumeric;
import static m.validation.ExponentType.propositionProposition;
import static m.validation.ExponentType.propositionPropositionProposition;
import static m.validation.PolymorphicType.entityList;
import static m.validation.ProductType.number2;
import static m.validation.ProductType.number3;
import static m.validation.ProductType.number4;
import static m.validation.Type.clip;
import static m.validation.Type.declaration;
import static m.validation.Type.entity;
import static m.validation.Type.fontType;
import static m.validation.Type.imageType;
import static m.validation.Type.materialType;
import static m.validation.Type.meshType;
import static m.validation.Type.number;
import static m.validation.Type.proposition;
import static m.validation.Type.textType;
import static m.validation.Type.unit;

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
