// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Pawn.h"
#include "Systems.generated.h"

UCLASS()
class PONGPLUSPLUS_API ASystems : public APawn
{
	GENERATED_BODY()
	
public:	
	// Sets default values for this actor's properties
	ASystems();

protected:
	// Called when the game starts or when spawned
	virtual void BeginPlay() override;

public:	
	// Called every frame
	virtual void Tick(float DeltaTime) override;

	virtual void SetupPlayerInputComponent(class UInputComponent* PlayerInputComponent) override;

private:
	void Handle(float Value);
	
};
