#version 330 core

in vec2 aTexCoord;
in vec2 aOffsetTexCoord;

out vec4 fragColor;

uniform sampler2D first_texture;
uniform sampler2D second_texture;


void main() {

  vec4 first = texture(first_texture, aTexCoord);
  vec4 second = texture(second_texture,  aOffsetTexCoord * -1);

  fragColor = vec4(mix(first,
                       second, 0.3f).rgb, 1.0f);
}