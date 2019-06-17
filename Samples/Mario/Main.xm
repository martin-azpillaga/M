<xml xmlns="http://www.w3.org/1999/xhtml">
  <block type="system" id="Y{s-X-?gG:L8E%CDwJq+" x="335" y="158">
    <field name="NAME">test</field>
    <statement name="commands">
      <block type="declaration" id="nq|-h9f{5R.|*XB^H)Tp">
        <field name="NAME">a</field>
        <next>
          <block type="if" id="U%6.Sd+TPXREGSt-kCxy">
            <value name="condition">
              <block type="binary_boolean" id="Tr)mK5?FFD^-8EE*)Ba+">
                <field name="OPERATION">OR</field>
                <value name="left">
                  <block type="binary_arithmetic_boolean" id="mQj;=[-Cdk/G$eB#y9#5">
                    <field name="OPERATION">LOWER</field>
                    <value name="left">
                      <block type="pop" id="0uNw0j7ev8}Fr]S#-g3,">
                        <field name="VARIABLE">a</field>
                      </block>
                    </value>
                    <value name="right">
                      <block type="pop" id="Bh.9d~r?-Wc)%3gKRcMp">
                        <field name="VARIABLE">a</field>
                      </block>
                    </value>
                  </block>
                </value>
                <value name="right">
                  <block type="binary_arithmetic_boolean" id="c][[v!hos`vcGX`=BjU;">
                    <field name="OPERATION">LOWER</field>
                    <value name="left">
                      <block type="pop" id="QE*rd$Ts*Y=Ysz[O/z7@">
                        <field name="VARIABLE">a</field>
                      </block>
                    </value>
                    <value name="right">
                      <block type="pop" id="iuX#WoB^MnY;(X;m~31E">
                        <field name="VARIABLE">a</field>
                      </block>
                    </value>
                  </block>
                </value>
              </block>
            </value>
            <statement name="ifCommands">
              <block type="break" id="q1jz![$h9fFQLp~-bse_"></block>
            </statement>
            <statement name="elseCommands">
              <block type="break" id="SbkVdryO!#SQ+}~vztsK"></block>
            </statement>
            <next>
              <block type="while" id="7b4bHP/6;/12%i*l^npW">
                <value name="condition">
                  <block type="unary_boolean" id="*l1H~0U3f}FJ`Ra,?ZGQ">
                    <value name="NAME">
                      <block type="binary_arithmetic_boolean" id="usZ0U2vV+R+J(N9ac8PE">
                        <field name="OPERATION">EQUAL</field>
                        <value name="left">
                          <block type="pop" id="4b4Ha@r?:Kcig3_HXXK]">
                            <field name="VARIABLE">a</field>
                          </block>
                        </value>
                        <value name="right">
                          <block type="pop" id="xEB[2su1Amb1R+8^K1un">
                            <field name="VARIABLE">a</field>
                          </block>
                        </value>
                      </block>
                    </value>
                  </block>
                </value>
                <statement name="commands">
                  <block type="break" id="RqqeKEE#)Jh=RN|GSn4M"></block>
                </statement>
              </block>
            </next>
          </block>
        </next>
      </block>
    </statement>
  </block>
</xml>