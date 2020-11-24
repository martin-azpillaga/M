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
			auto location = Actor->GetActorLocation() + GetInputAxisValue(inputName->Value) * direction->Value;
			Actor->SetActorLocation(location);
			TArray<AActor*> arr;
			Actor->GetOverlappingActors(arr);
			for (TActorIterator<AActor> ItB(GetWorld()); ItB; ++ItB)
			{
				AActor* b = *ItB;

				auto bInput = b->FindComponentByClass<UInputName>();
				if (bInput && arr.Contains(b))
				{
					GEngine->AddOnScreenDebugMessage(-1, 1.0f, FColor::Red, FString::Printf(TEXT("%d"), arr.Num()));
				}
			}
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

