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
  <block type="system" id="RW|6_`8,V!z+1Sb06.%|" x="308" y="1295">
    <field name="NAME">test</field>
    <statement name="commands">
      <block type="loop" id="QCc#6CLFG^m1T*D3Pai8">
        <field name="GROUP">a</field>
        <statement name="constraints">
          <block type="tagged" id="2z{;RufL$P;8sITd0mH4">
            <field name="negated">TRUE</field>
            <field name="NAME">name</field>
            <next>
              <block type="timeout" id=";nBcNha3D(oH|y@5=xc3">
                <field name="negated">FALSE</field>
                <field name="NAME">name</field>
                <next>
                  <block type="detection" id="q(?VQ6YfmA4K]2Ee@oO=">
                    <field name="negated">TRUE</field>
                    <field name="NAME">ENTER</field>
                    <next>
                      <block type="triggered" id="~Jh|r?!N9F*klia)I3`K">
                        <field name="negated">FALSE</field>
                        <field name="NAME">name</field>
                        <next>
                          <block type="mouse" id="bNr#:0!@$Fv%2k2X,@)3">
                            <field name="negated">TRUE</field>
                            <field name="NAME">DOWN</field>
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
        <statement name="commands">
          <block type="declaration" id="BIhxx/5$2U3Ms7MSO|av">
            <field name="NAME">counter</field>
            <next>
              <block type="if" id="d=(Qf}x}l:a)CWQ:n4I_">
                <value name="condition">
                  <block type="unary_boolean" id="3H{M`ZuZgnAZ5DpG}DKo">
                    <value name="NAME">
                      <block type="binary_arithmetic_boolean" id="QKMe=hfqSx=-4BL%~:Mx">
                        <field name="OPERATION">NOTEQUAL</field>
                        <value name="left">
                          <block type="access" id="o|#J+ut0-}hF9p+n7O#1">
                            <field name="COMPONENT">real1</field>
                            <field name="ENTITY">a</field>
                          </block>
                        </value>
                        <value name="right">
                          <block type="access" id="/lkhCG9!AO)Gd^jOr9)o">
                            <field name="COMPONENT">real1</field>
                            <field name="ENTITY">a</field>
                          </block>
                        </value>
                      </block>
                    </value>
                  </block>
                </value>
                <statement name="ifCommands">
                  <block type="while" id=":[1E$PHD|7d-Lgiiy2.{">
                    <value name="condition">
                      <block type="binary_boolean" id="%KkM{sj8kH?A*#IDCyRW">
                        <field name="OPERATION">OR</field>
                        <value name="left">
                          <block type="binary_arithmetic_boolean" id="H.ujKjbxNmu24T(f*$,d">
                            <field name="OPERATION">LOWER</field>
                            <value name="left">
                              <block type="pop" id="EeI^!hU,Lq2]X0kY*Li4">
                                <field name="VARIABLE">counter</field>
                              </block>
                            </value>
                            <value name="right">
                              <block type="access" id="?/QcC.;z2rKETfP=Mt8r">
                                <field name="COMPONENT">real1</field>
                                <field name="ENTITY">a</field>
                              </block>
                            </value>
                          </block>
                        </value>
                        <value name="right">
                          <block type="binary_arithmetic_boolean" id="K`eT|;zy4jS^P8;1P_Xv">
                            <field name="OPERATION">LOWER</field>
                            <value name="left">
                              <block type="pop" id="fHn?oIjYds9y/dcZ+Kh=">
                                <field name="VARIABLE">counter</field>
                              </block>
                            </value>
                            <value name="right">
                              <block type="access" id="DkRMdjx2=95*r}cB,vb~">
                                <field name="COMPONENT">real1</field>
                                <field name="ENTITY">a</field>
                              </block>
                            </value>
                          </block>
                        </value>
                      </block>
                    </value>
                    <statement name="commands">
                      <block type="create" id="1nZF)zrib$wV`DAo*#p]">
                        <value name="NAME">
                          <block type="access" id="AR1:G[Eu}`@[#^@2EbQs">
                            <field name="COMPONENT">reference</field>
                            <field name="ENTITY">a</field>
                          </block>
                        </value>
                        <next>
                          <block type="variable_assignment" id="}%00Ls~0Lq][4{$hD`@h">
                            <field name="assignmentType">SET</field>
                            <field name="NAME">created</field>
                            <value name="NAME">
                              <block type="create_and_save" id="81b^+Li(`m+1Na@kC~ZZ">
                                <value name="NAME">
                                  <block type="access" id="~H`G.o%9L}zpi-`U01Wo">
                                    <field name="COMPONENT">reference</field>
                                    <field name="ENTITY">a</field>
                                  </block>
                                </value>
                              </block>
                            </value>
                            <next>
                              <block type="destroy" id="DoXNGkP)enfO:w]#8CYt">
                                <field name="ENTITY">a</field>
                                <next>
                                  <block type="add" id="yl-D3S_MEWhtuz}uPLV~">
                                    <field name="COMPONENT">tag</field>
                                    <value name="ENTITY">
                                      <block type="pop" id="*2CHRokV+tItr;kNJ8h`">
                                        <field name="VARIABLE">created</field>
                                      </block>
                                    </value>
                                    <next>
                                      <block type="variable_assignment" id="n#1o6Gl7XY|=k~.CW(BH">
                                        <field name="assignmentType">SET</field>
                                        <field name="NAME">added</field>
                                        <value name="NAME">
                                          <block type="add_and_save" id="xxFB!0U[S^vL4DDz4q?V">
                                            <field name="COMPONENT">real2</field>
                                            <value name="ENTITY">
                                              <block type="pop" id="zlG/vv1(/}gX!P}/(Pl+">
                                                <field name="VARIABLE">a</field>
                                              </block>
                                            </value>
                                          </block>
                                        </value>
                                        <next>
                                          <block type="remove" id="{q|-%YBUODQl100JzZwM">
                                            <field name="COMPONENT">tag</field>
                                            <value name="ENTITY">
                                              <block type="pop" id="3hY:9%tZ;GoTcz30U{9r">
                                                <field name="VARIABLE">a</field>
                                              </block>
                                            </value>
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
                  </block>
                </statement>
                <statement name="elseCommands">
                  <block type="component_assignment" id=".nxH9NO0}:{`:#dd{,Cv">
                    <field name="assignmentType">SET</field>
                    <field name="COMPONENT">real1</field>
                    <field name="ENTITY">a</field>
                    <value name="expression">
                      <block type="binary_arithmetic" id="@wE8Cblw|7Oc2hVd0QUy">
                        <field name="OPERATION">ADD</field>
                        <value name="left">
                          <block type="unary_arithmetic" id="2k}{,jFL/kDRKuQ#,V4;">
                            <field name="OPERATION">INCREMENT</field>
                            <value name="value">
                              <block type="access" id="b?/_ztJDy*!cl[|=5Qd~">
                                <field name="COMPONENT">real1</field>
                                <field name="ENTITY">a</field>
                              </block>
                            </value>
                          </block>
                        </value>
                        <value name="right">
                          <block type="transformation" id="Gi8NT?MtM^gmzz8j~*bO">
                            <field name="OPERATION">SIN</field>
                            <value name="NAME">
                              <block type="access" id="IsVE/Xbb%^8)4udrJcr?">
                                <field name="COMPONENT">real1</field>
                                <field name="ENTITY">a</field>
                              </block>
                            </value>
                          </block>
                        </value>
                      </block>
                    </value>
                  </block>
                </statement>
                <next>
                  <block type="break" id="UH:nLSH0``8X$[rg%Y2H"></block>
                </next>
              </block>
            </next>
          </block>
        </statement>
      </block>
    </statement>
  </block>
</xml>