### 1. Introduction

The assignment will involve using modern OpenGL to render a scene. Scene graphs are required in the modelling
process and animation controls are required for hierarchical models.

该作业将涉及使用现代 OpenGL 渲染场景。建模过程中需要场景图，分层模型需要动画控制。

### 2. Learning outcomes

After completing this assignment, you will be able to:

完成此作业后，您将能够：

• Use data structures and mathematics in representing and manipulating 3D objects

• 使用数据结构和数学来表示和操作 3D 对象

• Produce interactive software that makes use of a graphics API

• 制作使用图形 API 的交互式软件

### 3. Requirements

Figure 1 shows a snowy scene containing two aliens and a security spotlight. The whole scene can be modelled
using transformed planes, cubes and spheres. The background to the scene should be animating, e.g. snow falling. 

图 1 显示了一个包含两个外星人和一个安全聚光灯的雪景。整个场景可以使用变换后的平面、立方体和球体进行建模。场景的背景应该是动画的，例如雪花飘落。

You must satisfy all the following requirements.

您必须满足以下所有要求。

### 3.1 The backdrop 3.1 背景

• In Figure 1, this is made of two planes with pictures of snowy scenes on them. The vertical plane is the
view behind the aliens. They are standing on the horizontal plane. This is a minimal backdrop.

• 在图1 中，它由两个planes组成，上面有雪景图片。垂直面是外星人背后的视野。他们站在水平面上。这是一个最小的背景。

• The backdrop must be a snowy scene.

• 背景必须是雪景。

• The backdrop can be improved by making sure the texture maps on the planes match each other at the
join

• 通过确保平面上的纹理贴图在连接处相互匹配可以改善背景

• The vertical plane can be improved by making it an animated texture map, e.g. snow falling.

• 可以通过使其成为动画纹理贴图来改进垂直平面，例如雪花飘落。

• The backdrop as a whole could be improved if the scene surrounded the aliens, either by using more
vertical planes around the aliens or by using a skybox or by using a combination of planes and skybox

• 如果场景围绕外星人，则可以改善整体背景，可以通过在外星人周围使用更多垂直平面或使用天空盒或使用平面和天空盒的组合

• The quality of what you produce for this part of the scene will be part of the marking. Some possibles
indicated above are more advanced than others. You must choose what to try.

• 您为这部分场景制作的质量将成为标记的一部分。上面指出的一些可能比其他更先进。你必须选择要尝试什么。

### 3.2 The security spotlight 3.2 安全聚光灯

• There is a security spotlight next to the aliens.

• 外星人旁边有安全聚光灯。

• The spotlight continuously rotates around the top of its pole so that sometimes it points at the aliens.

• 聚光灯围绕其杆顶持续旋转，有时会指向外星人。

• The spotlight is made of transformed spheres.

• 聚光灯由变形的球体组成。

• This is an advanced requirement as you are responsible for working out how to implement a
spotlight effect. (The relevant section in Joey’s online tutorial might help.)

• 这是一项高级要求，因为您负责研究如何实现聚光灯效果。 （乔伊在线教程中的相关部分可能会有所帮助。）

### 3.3 The aliens

• Each hierarchical model of an alien in Figure 1 is made up of 10 transformed spheres – a body, two arms, a
head, two eyes, two ears and an antenna (which is made of two spheres).

• 图1 中外星人的每个层次模型均由10 个变换后的球体组成——一个身体、两条手臂、一个头、两只眼睛、两只耳朵和一个天线（由两个球体组成）。

• The hierarchy and associated transformations are more important than the quality of the individual pieces in 
the hierarchy. I want you to demonstrate that you understand transformations and a scene graph hierarchy.

• 层次结构和相关转换比层次结构中各个部分的质量更重要。我希望您证明您了解转换和场景图层次结构。

• The model for each alien is the same.

• 每个外星人的模型都是相同的。

• In Figure 1, the aliens are grey. You must texture-map each part of each alien. You must decide on texture
for each alien model part and all the texture for one alien must be different to the other alien. You cannot
use the same texture on each alien.

• 在图1 中，外星人是灰色的。您必须对每个外星人的每个部分进行纹理映射。您必须决定每个外星人模型部件的纹理，并且一个外星人的所有纹理必须
与另一个外星人不同。你不能对每个外星人使用相同的纹理。

• I’ll be looking for a little creativity in the texturing. For example, have you considered both diffuse and
specular maps?

• 我会在纹理上寻找一点创意。例如，您是否考虑过漫反射贴图和镜面反射贴图？

• Each alien can rock its whole body from side to side.

• 每个外星人都可以左右摇摆整个身体。

• Each alien can roll its head around its body a little. This can be side to side or forwards and backwards.

• 每个外星人都可以将头绕着身体转动一点。这可以是左右或前后。

• An alien’s head should always remain connected to its body – as long as the rolling movement is
approximately correct that is acceptable.

• 外星人的头部应始终与身体保持连接 —— 只要滚动运动大致正确即可。

• The aliens could animate synchronously or separately or both. You choose. This will affect the number of
buttons on the user interface. 

• 外星人可以同步或单独或两者同时动画。你选。这将影响用户界面上的按钮数量。

• I’ll be looking for a little creativity in the animation.

• 我会在动画中寻找一点创意。

### 3.4 General illumination 一般照明

• The scene should be illuminated with at least two general world lights which can be positioned
anywhere in the world.

• 场景应使用至少两个可放置在世界任何地方的通用世界灯进行照明。

• These general world lights will illuminate all parts of the scene and help visualise the scene during
development and testing. 

• 这些通用世界灯将照亮场景的所有部分，并有助于在开发和测试期间可视化场景。

• When you switch off the general light(s), the effects of the security spotlight will be much clearer.

• 当您关闭普通灯时，安全聚光灯的效果会更加清晰。

• You do NOT have to do shadows. Do not worry about shadow effects.

• 您不必制作阴影。不用担心阴影效果。

### 3.5 User interface

• A user-controlled core.camera should be positioned in the scene. Use the core.camera that was given in the tutorial
material – the mouse can be used to change the direction the core.camera is pointing in and the keys can be used 
to move about. Do not change the key mappings from the one in the tutorial. If you change the key mappings it 
will make it difficult to mark. It doesn’t matter that the core.camera can see outside the room.

• 用户控制的摄像机应放置在场景中。使用教程材料中提供的相机 - 鼠标可用于更改相机指向的方向，
按键可用于移动。请勿更改教程中的键映射。如果更改键映射，标记将会变得困难。摄像头可以看到房间外面并不重要。

• It should be possible to turn each of the general lights on and off (or dim, i.e. reduce the intensity) from the
interface. 

• 应该可以从界面打开和关闭每个普通灯（或调暗，即降低强度）。

• It should be possible to turn the security spotlight(bulb) on and off.

• 应可以打开和关闭安全聚光灯（灯泡）。

• There should be buttons to control each alien’s movements, i.e. ‘Rock’ and ‘Roll’. The number of buttons will be 
determined by whether your aliens animate individually or synchronously or both. As an example, for a button labelled 
‘Rock 1’, the first alien would rock from side to side for either a predetermined time period or continuously until a
stop button is pressed. You choose

• 应该有按钮来控制每个外星人的动作，即“摇滚”和“滚动”。按钮的数量将取决于你的外星人是单独动画还是同步动画或两者兼而有之。例如，对于标记为“Rock 1”的按钮，
第一个外星人会在预定时间段内从一侧摇到另一侧，或者连续摇动，直到按下停止按钮。你选

### 3.6 Animation

• The animations are not straightforward and you may decide not to do this part, although that would affect
your marks for this part.

• 动画并不简单，您可能决定不执行此部分，尽管这会影响您对此部分的分数。

• It is perfectly acceptable to animate the Euler angles to achieve movement of the hierarchy. Do not
consider using quaternions, as this is beyond the requirements for this assignment.

• 通过动画欧拉角来实现层次结构的移动是完全可以接受的。不要考虑使用四元数，因为这超出了本作业的要求。

[//]: # (freepik)


