#version 330 core

in vec2 aTexCoord;
in vec2 aOffsetTexCoord;
in vec3 aPos;
in vec3 aNormal;

out vec4 fragColor;

uniform vec3 viewPos;
uniform sampler2D first_texture;
uniform sampler2D second_texture;

struct Light {
    vec3 position;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

uniform Light light;
uniform Light light_2;

struct Material {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    float shininess;
};

uniform Material material;

void main() {
    // ambient
    vec3 ambient = light.ambient * texture(first_texture, aTexCoord).rgb;

    // diffuse
    vec3 norm = normalize(aNormal);
    vec3 lightDir = normalize(light.position - aPos);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = light.diffuse * (diff) * texture(first_texture, aTexCoord).rgb;

    // specular
    vec3 viewDir = normalize(viewPos - aPos);
    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    vec3 specular = light.specular * (spec * material.specular);

    // ambient
    vec3 ambient_2 = light_2.ambient * texture(first_texture, aTexCoord).rgb;

    // diffuse
    norm = normalize(aNormal);
    lightDir = normalize(light_2.position - aPos);
    diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse_2 = light_2.diffuse * (diff) * texture(first_texture, aTexCoord).rgb;

    // specular
    viewDir = normalize(viewPos - aPos);
    reflectDir = reflect(-lightDir, norm);
    spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    vec3 specular_2 = light_2.specular * (spec * material.specular);


    vec4 first = texture(first_texture, aTexCoord);
    vec4 second = texture(second_texture, aOffsetTexCoord * -1);

    vec3 result_light1 = ambient + diffuse + specular;
    vec3 result_light2 = ambient_2 + diffuse_2 + specular_2;

    fragColor = vec4(mix(first,
    second, 1.0f).rgb  + result_light1 + result_light2, 1.0);
}