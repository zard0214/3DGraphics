#version 330 core

// https://learnopengl.com/Advanced-OpenGL/Cubemaps

layout (location = 0) in vec3 position;

out vec3 texCoords;

uniform mat4 projection;
uniform mat4 view;

void main()
{
    texCoords = position;
    gl_Position = projection * view * vec4(-position.x, -position.y, position.z, 1.0);

}