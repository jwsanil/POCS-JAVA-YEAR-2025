package org.example.core17;

public sealed class Animal permits Dog , Cat {
}

final class Dog extends Animal{}
non-sealed class Cat extends Animal{}//which further allows subclassing

 class A extends  Cat {}

