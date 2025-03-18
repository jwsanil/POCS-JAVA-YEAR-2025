package org.example.core17;

public sealed interface BInterface permits C,D,E{
    void m1();

}

non-sealed  class   D   implements  BInterface{
    @Override
    public void m1() {

    }
}
  non-sealed interface    E extends BInterface {

  }

final class C extends D implements BInterface{

}


