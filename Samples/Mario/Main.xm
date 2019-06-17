<xml xmlns="http://www.w3.org/1999/xhtml">
  <block type="entity" id="*GngT.o{Ij}$lUo@e7iA" x="426" y="213">
    <field name="NAME">name</field>
    <statement name="components">
      <block type="real1" id="Unf*x-^mn)tMd!|V_,k%">
        <field name="NAME">component</field>
        <field name="X">0</field>
      </block>
    </statement>
  </block>
  <block type="system" id="DeN/qb,8E9WnT*%^D#ir" x="430" y="421">
    <field name="NAME">name</field>
    <statement name="commands">
      <block type="loop" id="bZG0Nk7wy2lVXtFQm+$=">
        <field name="GROUP">a</field>
        <statement name="commands">
          <block type="declaration" id="u$OuSb5@rIJz}1cs,P~i">
            <field name="NAME">x</field>
            <next>
              <block type="component_assignment" id="D/qx6s9{v2kX!bIUvZ2:">
                <field name="assignmentType">INCREASE</field>
                <field name="COMPONENT">component</field>
                <field name="ENTITY">a</field>
                <value name="expression">
                  <block type="transformation" id="IGX+tFxaI!+7s,{dqh}E">
                    <field name="OPERATION">sin</field>
                    <value name="NAME">
                      <block type="pop" id="t[)?zR62Qa$T9rn)$XUF">
                        <field name="VARIABLE">x</field>
                      </block>
                    </value>
                  </block>
                </value>
                <next>
                  <block type="variable_assignment" id="#=X+Ax%t=wO|lOWrYWf.">
                    <field name="assignmentType">MULTIPLY</field>
                    <field name="NAME">x</field>
                    <value name="NAME">
                      <block type="binary_arithmetic" id="Bxw*895H^!3!(ENsS8H8">
                        <field name="OPERATION">MODULUS</field>
                        <value name="left">
                          <block type="unary_arithmetic" id="LYKu,B*BP(qnjQE$5AV(">
                            <field name="OPERATION">INCREMENT</field>
                            <value name="value">
                              <block type="pop" id="VDL3f|KxGMR3(gF(*YI`">
                                <field name="VARIABLE">x</field>
                              </block>
                            </value>
                          </block>
                        </value>
                        <value name="right">
                          <block type="pop" id="W{Z:^`+OUz$3l59hpSx,">
                            <field name="VARIABLE">x</field>
                          </block>
                        </value>
                      </block>
                    </value>
                  </block>
                </next>
              </block>
            </next>
          </block>
        </statement>
      </block>
    </statement>
  </block>
</xml>