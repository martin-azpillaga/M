#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Pawn.h"
#include "MyPawn.generated.h"

UCLASS()
class PONGPLUSPLUS_API AMyPawn : public APawn
{
	GENERATED_BODY()

public:
	AMyPawn();
	
	virtual void SetupPlayerInputComponent(class UInputComponent* PlayerInputComponent) override;

	virtual void Tick(float DeltaTime) override;

private:
	void Control(float DeltaTime);
	void Overlaps(float DeltaTime);
	void Serve(float DeltaTime);
};
