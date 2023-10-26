#version 330 core

layout (location = 0) in vec3 position;

out vec3 texCoords;

uniform mat4 projection;
uniform mat4 view;

void main()
{
    gl_Position = projection * view * vec4(-position.x, -position.y, position.z, 1.0);
    texCoords = position;
}