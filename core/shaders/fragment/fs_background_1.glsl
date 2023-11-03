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
uniform Light light2;

struct Material {
  vec3 ambient;
  vec3 diffuse;
  vec3 specular;
  float shininess;
};

uniform Material material;

vec3 calcLight(Light light)
{
  // ambient
  vec3 ambient = light.ambient * texture(first_texture, aTexCoord).rgb;

  // diffuse
  vec3 norm = normalize(aNormal);
  vec3 lightDir = normalize(light.position - aPos);
  float diff = max(dot(norm, lightDir), 0.0);
  vec3 diffuse = light.diffuse * (diff * texture(first_texture, aTexCoord).rgb);

  // specular
  vec3 viewDir = normalize(viewPos - aPos);
  vec3 reflectDir = reflect(-lightDir, norm);
  float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
  vec3 specular = light.specular * texture(second_texture, aOffsetTexCoord * -1).rgb;

  vec3 result = ambient + diffuse + specular;

  return result;
}

void main() {
  vec4 first = texture(first_texture, aTexCoord);
  vec4 second = texture(second_texture,  aOffsetTexCoord * -1);

  vec3 result = calcLight(light) + calcLight(light2);
  fragColor = vec4(mix(first,
                         second, 1.0f).rgb  + result, 1.0);
}