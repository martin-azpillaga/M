#pragma once

#include "CoreMinimal.h"
#include "Components/ActorComponent.h"
#include "Ready.generated.h"


UCLASS( ClassGroup=(Custom), meta=(BlueprintSpawnableComponent) )
class PONGPLUSPLUS_API UReady : public UActorComponent
{
	GENERATED_BODY()

public:
	UPROPERTY(EditAnywhere, BlueprintReadWrite, Category = "M")
	bool Value;
};
