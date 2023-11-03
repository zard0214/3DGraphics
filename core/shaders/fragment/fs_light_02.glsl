#version 330 core

out vec4 fragColor;

struct Material {
    float brightness;
};

uniform Material material;

void main() {
    fragColor = vec4(material.brightness);
}