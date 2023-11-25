package com.plcoding.jetpackcomposepokedex.presentation.pokemondetail.ui.pokemonDetailScreenContent

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.jetpackcomposepokedex.R
import com.plcoding.jetpackcomposepokedex.domain.models.Pokemon
import com.plcoding.jetpackcomposepokedex.presentation.pokemondetail.ui.utils.PokemonDetailDataItem
import com.plcoding.jetpackcomposepokedex.util.parseStatToAbbr
import com.plcoding.jetpackcomposepokedex.util.parseStatToColor
import com.plcoding.jetpackcomposepokedex.util.parseTypeToColor
import java.util.Locale
import kotlin.math.round

@Composable
fun PokemonDetailScreenContent(
    pokemonInfo: Pokemon,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()
    val pokemonWeightInKg = remember {
        round(pokemonInfo.weight * 100f) / 1000f
    }
    val pokemonHeightInMeters = remember {
        round(pokemonInfo.height * 100f) / 1000f
    }
    val maxBaseStat = remember {
        pokemonInfo.stats.maxOf { it.base_stat }
    }
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 100.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "#${pokemonInfo.id} ${pokemonInfo.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }}",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            for ( type in pokemonInfo.types ) {
                Box(
                    contentAlignment =  Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(parseTypeToColor(type))
                        .height(35.dp)
                ) {
                    Text(
                        text = type.type.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        },
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            PokemonDetailDataItem(
                dataValue = pokemonWeightInKg, dataUnit = "kg",
                dataIcon = painterResource(
                    id = R.drawable.ic_weight
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier
                .size(1.dp, 80.dp)
                .background(Color.LightGray)
            )
            PokemonDetailDataItem(
                dataValue = pokemonHeightInMeters, dataUnit = "mts",
                dataIcon = painterResource(
                    id = R.drawable.ic_height
                ),
                modifier = Modifier.weight(1f)
            )
        }
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Base stats:",
                fontSize = 20.sp,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))

            for (i in pokemonInfo.stats.indices) {
                val stat = pokemonInfo.stats[i]
                val curPercent = animateFloatAsState(
                    targetValue = if ( animationPlayed ) {
                        stat.base_stat / maxBaseStat.toFloat()
                    } else 0f,
                    animationSpec = tween(
                        1000,
                        0
                    ),
                    label = ""
                )
                LaunchedEffect(key1 = true) {
                    animationPlayed = true
                }
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSystemInDarkTheme()) {
                                Color(0xFF505050)
                            } else {
                                Color.LightGray
                            }
                        )
                ) {
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(curPercent.value)
                            .clip(CircleShape)
                            .background(parseStatToColor(stat))
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = parseStatToAbbr(stat),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = (curPercent.value * maxBaseStat).toInt().toString(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}