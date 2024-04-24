# SparkButtonCompose
<p>
A SparkButtonCompose is used to create a sparking effect like twitter's like button.🎊
</p>
</br>
<p >
<img src="https://github.com/JustinGeorgeJoseph/SparkButtonCompose/blob/main/demo/like-button.gif" alt="gif" title="gif" width="300" height="650" />
</p>

## Including in your project
[![](https://jitpack.io/v/JustinGeorgeJoseph/SparkButtonCompose.svg)](https://jitpack.io/#JustinGeorgeJoseph/SparkButtonCompose)


## Dependency
```
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

Add Dependency
```
dependencies {
    implementation 'com.github.JustinGeorgeJoseph:SparkButtonCompose:1.0.0'
}
```

## Usage

```
fun SparkButton(
    modifier: Modifier = Modifier,
    @DrawableRes enabledIcon: Int,
    @DrawableRes disableIcon: Int,
    dotSize: Float = 10f,
    animationTime: Int = 500,
    bigDotColor: Color = Color.Black,
    smallDotColor: Color = Color.Blue,
    enabledIconTintColor: Color = LocalContentColor.current,
    disableIconTintColor: Color = LocalContentColor.current,
    onClickListener: (Boolean) -> Unit = {}
)
```

### Add enable/disable icon
User can add both enable and disable icon for both states
```
@DrawableRes enabledIcon: Int,
@DrawableRes disableIcon: Int,
```

### Dot size
User can change the dot size in the animation and the default size is `10f`
```
dotSize: Float = 10f,
```

### Animation time
User can control the animation time and default time is `500 milliseconds`
```
animationTime: Int = 500,
```

### Background color of dots
User can change the background color of dots
```
bigDotColor: Color = Color.Black,
smallDotColor: Color = Color.Blue,
```

### Tint color of the icons
User can change the tint color of icon
```
enabledIconTintColor: Color = LocalContentColor.current,
disableIconTintColor: Color = LocalContentColor.current,
```

### Click listener
User can add click listener to the button
```
onClickListener: (Boolean) -> Unit = {}
```


# License
```xml
   Copyright [2024] JustinGeorgeJoseph

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
