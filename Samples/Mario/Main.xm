<xml xmlns="http://www.w3.org/1999/xhtml">
  <block type="entity" id="QIB@@1Q=C_{fx/GGFCvJ" x="305" y="124">
    <field name="NAME">first</field>
    <statement name="components">
      <block type="tag" id="Qy;H5Uk3mH=l%Z^U+O{b">
        <field name="NAME">tag</field>
        <next>
          <block type="tag" id="eZz[NHN7^n()g:W!Cq#1">
            <field name="NAME">removed2</field>
          </block>
        </next>
      </block>
    </statement>
  </block>
  <block type="entity" id="(7Z7#6fi%ov[)aAZdkx!" x="302" y="324">
    <field name="NAME">tester</field>
    <value name="base">
      <block type="base" id="~N8ZSsX;`Qtt028sdPlk">
        <field name="NAME">tester</field>
        <statement name="removed">
          <block type="componentname" id="T(4{r=cK[Q/~13eOcdhe">
            <field name="NAME">tag</field>
            <next>
              <block type="componentname" id="E{^o?GtmBPPJ|_Qe6?qG">
                <field name="NAME">removed2</field>
              </block>
            </next>
          </block>
        </statement>
      </block>
    </value>
    <statement name="components">
      <block type="tag" id="qs_7nd6;`kQEEv?cs;Sk">
        <field name="NAME">tag</field>
        <next>
          <block type="real1" id="IDU/A#M!_E:reX?HVtF(">
            <field name="NAME">real1</field>
            <field name="X">1</field>
            <next>
              <block type="real2" id="gRQ,}kA27]-.@xs(D!=9">
                <field name="NAME">real2</field>
                <field name="X">2</field>
                <field name="Y">2</field>
                <next>
                  <block type="real3" id="lVL6S0N3nbcZ(Wg,+$)l">
                    <field name="NAME">real3</field>
                    <field name="X">3</field>
                    <field name="Y">3</field>
                    <field name="Z">3</field>
                    <next>
                      <block type="real4" id="-VdjOgk1s60W:,pJYioJ">
                        <field name="NAME">real4</field>
                        <field name="X">4</field>
                        <field name="Y">4</field>
                        <field name="Z">4</field>
                        <field name="W">4</field>
                        <next>
                          <block type="textcomponent" id="y4/yX-B(GbUm(aD[=U~*">
                            <field name="NAME">text</field>
                            <field name="VALUE">hello</field>
                            <next>
                              <block type="sprite" id=".Kn~j1-}/ap$c=ro#VH=">
                                <field name="NAME">appearance</field>
                                <field name="VALUE">name</field>
                                <next>
                                  <block type="audio" id="P~Gh6-9*ZJLM8bFv*!C6">
                                    <field name="NAME">clip</field>
                                    <field name="VALUE">name</field>
                                    <next>
                                      <block type="mesh" id="^KxxnT/vG7@k@!wqu]|P">
                                        <field name="NAME">mesh</field>
                                        <field name="VALUE">name</field>
                                        <next>
                                          <block type="timer" id="TuGr2S~hKacFLG9UA#2e">
                                            <field name="NAME">timer</field>
                                            <field name="VALUE">0</field>
                                            <next>
                                              <block type="trigger" id="BQ{cXrstaC1k6tP+O0sk">
                                                <field name="NAME">button</field>
                                                <field name="VALUE">EAST</field>
                                                <next>
                                                  <block type="range" id=",a23))V6X]#0kqYKOrx:">
                                                    <field name="NAME">tilter</field>
                                                    <field name="VALUE">RIGHTX</field>
                                                    <next>
                                                      <block type="sensor" id="sYfXheEDy(x*2$W7yzG!">
                                                        <field name="NAME">sensor</field>
                                                        <statement name="VALUES">
                                                          <block type="enumeration_value" id="ngOE2doTC0h})B!_$fF5">
                                                            <field name="NAME">cat1</field>
                                                            <next>
                                                              <block type="enumeration_value" id="gCn#rex8fPTgNIo5h7i9">
                                                                <field name="NAME">cat2</field>
                                                              </block>
                                                            </next>
                                                          </block>
                                                        </statement>
                                                        <next>
                                                          <block type="enumeration" id="L;mZ/^Zrue*Ee.H2H(Ly">
                                                            <field name="NAME">enumeration</field>
                                                            <statement name="VALUES">
                                                              <block type="enumeration_value" id="((87`}lZZ|@B?VtL#U7V">
                                                                <field name="NAME">red</field>
                                                                <next>
                                                                  <block type="enumeration_value" id="w04]7pQvrM~`Ku)aHowr">
                                                                    <field name="NAME">blue</field>
                                                                  </block>
                                                                </next>
                                                              </block>
                                                            </statement>
                                                            <next>
                                                              <block type="reference" id="o8)k^9oNsbOKH4(M6~p*">
                                                                <field name="NAME">reference</field>
                                                                <field name="VALUE">first</field>
                                                                <next>
                                                                  <block type="vector" id="n4CIk,Z$oD@/7Er[UfM8">
                                                                    <field name="NAME">vector</field>
                                                                    <field name="VALUE">MOUSEPOSITION</field>
                                                                    <next>
                                                                    
                                                                      <block type="font" id="}Q]u!:sv`?=2FVJeF}qP">
                                                                        <field name="NAME">font</field>
                                                                        <field name="VALUE">font</field>
                                                                        <next>
                                                                          <block type="click" id="yAq{S.RilBU!xG)`6upi">
                                                                            <field name="NAME">clicky</field>
                                                                          </block>
                                                                        </next>
                                                                      </block>
                                                                    </next>
                                                                  </block>
                                                                </next>
                                                              </block>
                                                            </next>
                                                          </block>
                                                        </next>
                                                      </block>
                                                    </next>
                                                  </block>
                                                </next>
                                              </block>
                                            </next>
                                          </block>
                                        </next>
                                      </block>
                                    </next>
                                  </block>
                                </next>
                              </block>
                            </next>
                          </block>
                        </next>
                      </block>
                    </next>
                  </block>
                </next>
              </block>
            </next>
          </block>
        </next>
      </block>
    </statement>
    <statement name="subEntities">
      <block type="entity" id="MbR*g840UDcW_`7Z;yNP">
        <field name="NAME">contained</field>
        <statement name="components">
          <block type="tag" id="h|Lv%Lx~9wof4b2~q|Az">
            <field name="NAME">something</field>
            <next>
              <block type="tag" id="c}[Pu$J)sn;*^q;6Mt@*">
                <field name="NAME">somethingelse</field>
              </block>
            </next>
          </block>
        </statement>
      </block>
    </statement>
  </block>
</xml>