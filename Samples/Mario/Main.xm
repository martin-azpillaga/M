<xml xmlns="http://www.w3.org/1999/xhtml">
  <block type="system" id="Y{s-X-?gG:L8E%CDwJq+" x="335" y="158">
    <field name="NAME">test</field>
    <statement name="commands">
      <block type="declaration" id="Yoe$q(RlBsqN|)NKv15:">
        <field name="NAME">name</field>
        <next>
          <block type="loop" id="*jLbik)J8bF;h_3L@~1+">
            <field name="GROUP">name</field>
            <statement name="constraints">
              <block type="constraint_tagged" id="9#n9SBJkb@VT@mN`0Oci">
                <field name="negated">TRUE</field>
                <field name="NAME">tag</field>
                <next>
                  <block type="constraint_timeout" id="Bzlvzs+_mrublGs`oBf[">
                    <field name="negated">FALSE</field>
                    <field name="NAME">timer</field>
                    <next>
                      <block type="constraint_triggered" id="EFGvH5E(:c[)tUvH_J+q">
                        <field name="negated">TRUE</field>
                        <field name="NAME">trigger</field>
                        <next>
                          <block type="constraint_detection" id="Ph_|?[5)n!_x9@},1x^I">
                            <field name="negated">FALSE</field>
                            <field name="NAME">sensor</field>
                            <field name="TYPE">STAY</field>
                            <next>
                              <block type="constraint_mouse" id="V`vznIkNOpXE_;u,Galy">
                                <field name="negated">TRUE</field>
                                <field name="NAME">clicky</field>
                                <field name="TYPE">DOWN</field>
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
              <block type="declaration" id="M3Iy6XY*s?B?^~cCUemS">
                <field name="NAME">o</field>
                <next>
                  <block type="break" id="thYUWU_`HZV]equJhsKe"></block>
                </next>
              </block>
            </statement>
          </block>
        </next>
      </block>
    </statement>
  </block>
</xml>