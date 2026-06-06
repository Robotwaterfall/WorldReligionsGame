<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.12.2" name="objects" tilewidth="80" tileheight="112" tilecount="15" columns="0">
 <grid orientation="orthogonal" width="1" height="1"/>
 <tile id="1" type="Object">
  <properties>
   <property name="animation" value="IDLE"/>
   <property name="animationSpeed" type="float" value="1"/>
   <property name="attackSound" value="SWING"/>
   <property name="damage" type="float" value="7"/>
   <property name="damageDelay" type="float" value="0.2"/>
   <property name="life" type="int" value="12"/>
   <property name="lifeReg" type="float" value="0.25"/>
   <property name="speed" type="float" value="3.5"/>
  </properties>
  <image source="objects/player.png" width="32" height="32"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="11" y="18" width="9" height="5">
    <ellipse/>
   </object>
   <object id="2" name="attack_sensor_down" x="0" y="17" width="32" height="15">
    <properties>
     <property name="sensor" type="bool" value="true"/>
    </properties>
   </object>
   <object id="3" name="attack_sensor_up" x="0" y="0" width="32" height="15">
    <properties>
     <property name="sensor" type="bool" value="true"/>
    </properties>
   </object>
   <object id="4" name="attack_sensor_left" x="0" y="0" width="15" height="32">
    <properties>
     <property name="sensor" type="bool" value="true"/>
    </properties>
   </object>
   <object id="5" name="attack_sensor_right" x="17" y="0" width="15" height="32">
    <properties>
     <property name="sensor" type="bool" value="true"/>
    </properties>
   </object>
  </objectgroup>
 </tile>
 <tile id="2" type="Prop">
  <image source="objects/house.png" width="80" height="112"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="7" y="82" width="67" height="26"/>
  </objectgroup>
 </tile>
 <tile id="4" type="Prop">
  <image source="objects/chest.png" width="16" height="16"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="0" y="4" width="16" height="10"/>
  </objectgroup>
 </tile>
 <tile id="5" type="Prop">
  <image source="objects/oak_tree.png" width="41" height="63"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="13" y="54">
    <polygon points="0,0 6,1 11,1 16,-1 16,-2 14,-5 13,-13 3,-13 3,-6 2,-5 1,-3 0,-1"/>
   </object>
  </objectgroup>
 </tile>
 <tile id="6" type="Object">
  <properties>
   <property name="animation" value="IDLE"/>
   <property name="z" type="int" value="0"/>
  </properties>
  <image source="objects/trap.png" width="16" height="16"/>
 </tile>
 <tile id="7" type="Object">
  <properties>
   <property name="animation" value="IDLE"/>
   <property name="animationSpeed" type="float" value="1"/>
   <property name="bodyType" value="StaticBody"/>
   <property name="life" type="int" value="99999"/>
   <property name="lifeReg" type="float" value="9999"/>
  </properties>
  <image source="objects/training_dummy.png" width="32" height="32"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="3" y="12" width="26" height="16"/>
  </objectgroup>
 </tile>
 <tile id="8" type="Object">
  <properties>
   <property name="animation" value="IDLE"/>
   <property name="animationSpeed" type="float" value="1"/>
   <property name="bodyType" value="StaticBody"/>
   <property name="life" type="int" value="99999"/>
   <property name="lifeReg" type="float" value="9999"/>
  </properties>
  <image source="objects/npc.png" width="64" height="64"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="17" y="33" width="28" height="31"/>
  </objectgroup>
 </tile>
 <tile id="9" type="Object">
  <properties>
   <property name="animation" value="IDLE"/>
   <property name="animationSpeed" type="float" value="1"/>
   <property name="bodyType" value="StaticBody"/>
   <property name="life" type="int" value="99999"/>
   <property name="lifeReg" type="float" value="9999"/>
  </properties>
  <image source="objects/key.png" width="20" height="20"/>
 </tile>
 <tile id="12">
  <properties>
   <property name="animation" value="IDLE"/>
   <property name="animationSpeed" type="float" value="0.1"/>
   <property name="bodyType" value="StaticBody"/>
   <property name="life" type="int" value="99999"/>
   <property name="lifeReg" type="float" value="9999"/>
  </properties>
  <image source="objects/pigeau.png" width="38" height="64"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="24.1712" y="14.0679">
    <polygon points="0,0 -6.52238,-0.383669 -10.487,1.66257 -11.3822,6.39449 -9.97541,12.789 -13.94,18.0325 -15.8583,23.4038 -16.242,29.5425 -15.4747,32.484 -12.4053,32.2282 -11.3822,36.0649 -11.8938,40.1574 -4.73192,40.669 -2.68569,35.5534 -0.383669,41.1805 6.90605,41.1805 5.24348,36.4486 6.52238,33.1235 8.44073,32.9956 10.7427,30.0541 10.6149,21.1018 6.90605,13.94 6.13871,9.33596 6.01082,4.22036"/>
   </object>
  </objectgroup>
 </tile>
 <tile id="19">
  <properties>
   <property name="bodyType" value="StaticBody"/>
   <property name="life" type="int" value="99999"/>
   <property name="lifeReg" type="float" value="9999"/>
  </properties>
  <image source="objects/elder_lady.png" width="14" height="19"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="1" width="13" height="18"/>
  </objectgroup>
 </tile>
 <tile id="20">
  <properties>
   <property name="bodyType" value="StaticBody"/>
   <property name="life" type="int" value="99999"/>
   <property name="lifeReg" type="float" value="9999"/>
  </properties>
  <image source="objects/community_helper.png" width="14" height="19"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="2" y="1" width="11" height="18"/>
  </objectgroup>
 </tile>
 <tile id="21">
  <properties>
   <property name="bodyType" value="StaticBody"/>
   <property name="life" type="int" value="99999"/>
   <property name="lifeReg" type="float" value="9999"/>
  </properties>
  <image source="objects/helper.png" width="14" height="19"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="1" width="12" height="18"/>
  </objectgroup>
 </tile>
 <tile id="22">
  <properties>
   <property name="bodyType" value="StaticBody"/>
   <property name="life" type="int" value="99999"/>
   <property name="lifeReg" type="float" value="9999"/>
  </properties>
  <image source="objects/car.png" width="30" height="78"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1.53468" y="14.0679">
    <polygon points="0,0 -0.12789,-6.65027 2.17413,-10.1033 7.03394,-13.5563 13.0448,-13.6842 19.4393,-13.6842 23.4038,-11.638 26.4732,-7.80128 26.9848,-3.19725 26.9848,0.895229 27.3684,48.8539 26.6011,52.4348 26.8569,54.9926 25.7059,57.1667 25.3222,59.0851 23.6596,61.8987 20.7182,62.9218 16.242,63.5612 10.1033,63.0497 5.88293,62.4102 2.94147,60.7477 0.767339,57.6783 -0.383669,53.4579 -1.15101,46.1682"/>
   </object>
  </objectgroup>
 </tile>
 <tile id="25">
  <properties>
   <property name="animation" value="IDLE"/>
   <property name="animationSpeed" type="float" value="1"/>
   <property name="bodyType" value="StaticBody"/>
   <property name="life" type="int" value="99999"/>
   <property name="lifeReg" type="float" value="9999"/>
  </properties>
  <image source="objects/camp_fire.png" width="28" height="26"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="0" y="1" width="28" height="25"/>
  </objectgroup>
 </tile>
 <tile id="26">
  <image source="objects/plane.png" width="30" height="30"/>
  <objectgroup draworder="index" id="2">
   <object id="1" x="6.14317" y="3" width="17.8167" height="17.353"/>
  </objectgroup>
 </tile>
</tileset>
