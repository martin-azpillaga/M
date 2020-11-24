// Fill out your copyright notice in the Description page of Project Settings.


#include "Systems.h"
#include "Direction.h"
#include "EngineUtils.h"

// Sets default values
ASystems::ASystems()
{
 	// Set this actor to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	PrimaryActorTick.bCanEverTick = true;

}

// Called when the game starts or when spawned
void ASystems::BeginPlay()
{
	Super::BeginPlay();
	
	
	//InputComponent->BindAxis("Control", this, &ASystems::Handle);
	
}

// Called every frame
void ASystems::Tick(float DeltaTime)
{
	Super::Tick(DeltaTime);
	
	// Find Actors by tag (also works on ActorComponents, use TObjectIterator instead)
	for (TActorIterator<AActor> It(GetWorld()); It; ++It)
	{
		AActor* Actor = *It;
		auto direction = Actor->FindComponentByClass<UDirection>();
		auto primitive = Actor->FindComponentByClass<UPrimitiveComponent>();
		if (direction && primitive)
		{
			direction->Value.X = direction->Value.X+1;
			auto location = Actor->GetTransform().GetTranslation();
			Actor->SetActorLocation(direction->Value);
		}
	}
	
}

void ASystems::SetupPlayerInputComponent(class UInputComponent* InputComponent)
{
	check(InputComponent);
	InputComponent->BindAxis("Control", this, &ASystems::Handle);
	GEngine->AddOnScreenDebugMessage(-1, 1.0f, FColor::Red, "jo");
}

void ASystems::Handle(float Value)
{
	GEngine->AddOnScreenDebugMessage(-1, 1.0f, FColor::Red, FString::Printf(TEXT("%f"), Value));
}

