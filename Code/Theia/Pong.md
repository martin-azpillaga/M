# Pong

## First system

* Create and open file main.m
* Append to main.m

~~~ m
control()
{
    foreach a
    {
        a.velocity = a.base * a.input
    }
}
~~~

* Expect new files in src-gen/Unity/Assets/Code: Systems/control.cs, Components/base.cs
