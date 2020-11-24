// Fill out your copyright notice in the Description page of Project Settings.


#include "MyPawn.h"
#include "Direction.h"
#include "EngineUtils.h"
#include "InputName.h"

// Sets default values
AMyPawn::AMyPawn()
{
 	// Set this pawn to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	PrimaryActorTick.bCanEverTick = true;

}

// Called when the game starts or when spawned
void AMyPawn::BeginPlay()
{
	Super::BeginPlay();
	
}

// Called every frame
void AMyPawn::Tick(float DeltaTime)
{
	Super::Tick(DeltaTime);

	for (TActorIterator<AActor> It(GetWorld()); It; ++It)
	{
		AActor* Actor = *It;
		auto direction = Actor->FindComponentByClass<UDirection>();
		auto inputName = Actor->FindComponentByClass<UInputName>();
		if (direction && inputName)
		{
			const FName n = inputName->Value;
				GEngine->AddOnScreenDebugMessage(-1, 1.0f, FColor::Red, FString::Printf(TEXT("%d"), InputComponent->GetAxisKeyValue(inputName->key)==1));
				auto location = Actor->GetActorLocation() + GetInputAxisValue(inputName->Value) * direction->Value;
				Actor->SetActorLocation(location);
			
		}
	}
}

// Called to bind functionality to input
void AMyPawn::SetupPlayerInputComponent(UInputComponent* PlayerInputComponent)
{
	Super::SetupPlayerInputComponent(PlayerInputComponent);

	for (TActorIterator<AActor> It(GetWorld()); It; ++It)
	{
		AActor* Actor = *It;
		auto inputName = Actor->FindComponentByClass<UInputName>();
		if (inputName)
		{
			PlayerInputComponent->BindAxis(inputName->Value);
			PlayerInputComponent->BindAxisKey(inputName->key);
		}
	}
}
