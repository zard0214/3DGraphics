#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;
layout (location = 2) in vec2 texCoord;

out vec3 aPos;
out vec3 aNormal;
out vec2 aTexCoord;
out vec2 aOffsetTexCoord;

uniform mat4 model;
uniform mat4 mvpMatrix;
uniform vec2 offset;

void main() {
  gl_Position = mvpMatrix * vec4(position, 1);
  aPos = vec3(model*vec4(position, 1.0));
  mat4 normalMatrix = transpose(inverse(model));
  vec3 norm = normalize(normal);
  aNormal = mat3(normalMatrix) * norm;

  aTexCoord = texCoord;

  aOffsetTexCoord = texCoord - offset;

}