#pragma once

#include "CoreMinimal.h"
#include "Components/ActorComponent.h"
#include "Direction.generated.h"


UCLASS( ClassGroup=(Custom), meta=(BlueprintSpawnableComponent) )
class PONGPLUSPLUS_API UDirection : public UActorComponent
{
	GENERATED_BODY()

public:
	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "M")
	FVector Value;
};
