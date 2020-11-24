#pragma once

#include "CoreMinimal.h"
#include "Components/ActorComponent.h"
#include "Boards.generated.h"


UCLASS( ClassGroup=(Custom), meta=(BlueprintSpawnableComponent) )
class PONGPLUSPLUS_API UBoards : public UActorComponent
{
	GENERATED_BODY()

public:
	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "M")
	TArray<AActor*> Value;
};
