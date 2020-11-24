#include "MyPawn.h"
#include "Direction.h"
#include "EngineUtils.h"
#include "InputName.h"
#include "RespawnPoint.h"
#include "Ready.h"
#include "StartingVelocity.h"

AMyPawn::AMyPawn()
{
	PrimaryActorTick.bCanEverTick = true;
}

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
		}
	}
}

void AMyPawn::Tick(float DeltaTime)
{
	Super::Tick(DeltaTime);

	Control(DeltaTime);
	Overlaps(DeltaTime);
	Serve(DeltaTime);
}

void AMyPawn::Control(float DeltaTime)
{
	for (TActorIterator<AActor> Ita(GetWorld()); Ita; ++Ita)
	{
		auto a = *Ita;
		auto a_direction = a->FindComponentByClass<UDirection>();
		auto a_inputName = a->FindComponentByClass<UInputName>();
		if (a_direction && a_inputName)
		{
			a->SetActorLocation(a->GetActorLocation() + GetInputAxisValue(a_inputName->Value) * a_direction->Value);
		}
	}
}

void AMyPawn::Overlaps(float DeltaTime)
{
	for (TActorIterator<AActor> Ita(GetWorld()); Ita; ++Ita)
	{
		auto a = *Ita;

		TArray<AActor*> overlaps_a;
		a->GetOverlappingActors(overlaps_a);

		for (TActorIterator<AActor> ItB(GetWorld()); ItB; ++ItB)
		{
			auto b = *ItB;
			auto b_respawnPoint = b->FindComponentByClass<URespawnPoint>();

			if (b_respawnPoint && overlaps_a.Contains(b))
			{
				a->SetActorLocation(b_respawnPoint->Value);
			}
		}
	}
}

void AMyPawn::Serve(float DeltaTime)
{
	for (TActorIterator<AActor> Ita(GetWorld()); Ita; ++Ita)
	{
		auto a = *Ita;

		auto a_ready = a->FindComponentByClass<UReady>();
		auto a_vel = a->FindComponentByClass<UStartingVelocity>();
		auto a_body = a->FindComponentByClass<UPrimitiveComponent>();

		if (a_ready && a_vel && a_body)
		{
			a_body->SetAllPhysicsLinearVelocity(a_vel->Value, false);
		}
	}
}