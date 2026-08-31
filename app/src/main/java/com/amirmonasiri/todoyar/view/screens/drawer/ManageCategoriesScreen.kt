package com.amirmonasiri.todoyar.view.screens.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.Regular
import com.adamglin.phosphoricons.duotone.ArrowRight
import com.adamglin.phosphoricons.duotone.PencilSimple
import com.adamglin.phosphoricons.duotone.Trash
import com.adamglin.phosphoricons.regular.Plus
import com.amirmonasiri.todoyar.model.Category
import com.amirmonasiri.todoyar.view.ui.theme.Dimens
import com.amirmonasiri.todoyar.view.ui.theme.VazirFontFamily
import com.amirmonasiri.todoyar.viewModel.CategoryViewModel

/**
 * Screen for managing task categories.
 *
 * Features:
 * - Display all categories
 * - Add new categories
 * - Edit custom categories
 * - Delete custom categories
 *
 * Default categories cannot be edited or deleted.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCategoriesScreen(
    navController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel()
) {

    val categories by viewModel.categories.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }

    var categoryName by remember { mutableStateOf("") }

    var showEditDialog by remember { mutableStateOf(false) }

    var categoryToEdit by remember { mutableStateOf<Category?>(null) }

    val fabPosition =
        if (LocalLayoutDirection.current == LayoutDirection.Ltr)
            FabPosition.End
        else
            FabPosition.Start

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "مدیریت دسته‌بندی‌ها",
                        fontFamily = VazirFontFamily
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = PhosphorIcons.Duotone.ArrowRight,
                            contentDescription = "بازگشت",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButtonPosition = fabPosition,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    categoryName = ""
                    showAddDialog = true
                },
                shape = CircleShape,
                modifier = Modifier.size(64.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = PhosphorIcons.Regular.Plus,
                    contentDescription = "افزودن دسته‌بندی",
                    modifier = Modifier.size(28.dp)

                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "دسته‌بندی‌ها",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = VazirFontFamily
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (categories.isEmpty()) {

                Text(
                    text = "هنوز دسته‌بندی‌ای وجود ندارد.",
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = VazirFontFamily
                )

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(
                        bottom = 80.dp
                    )
                ) {

                    items(
                        items = categories,
                        key = { it.id }
                    ) { category ->

                        CategoryItem(
                            category = category,
                            onEdit = {
                                categoryToEdit = category
                                categoryName = category.name
                                showEditDialog = true
                            },
                            onDelete = {
                                viewModel.deleteCategory(category)
                            }
                        )
                    }
                }
            }
        }
    }

    // Add Category Dialog

    if (showAddDialog) {

        AlertDialog(
            onDismissRequest = {
                showAddDialog = false
            },

            title = {
                Text(
                    text = "افزودن دسته‌بندی",
                    fontFamily = VazirFontFamily
                )
            },

            text = {

                OutlinedTextField(
                    value = categoryName,
                    onValueChange = {
                        categoryName = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text(
                            text = "نام دسته‌بندی",
                            fontFamily = VazirFontFamily
                        )
                    },
                    shape = RoundedCornerShape(
                        Dimens.CornerBottomSheet
                    )
                )
            },

            confirmButton = {

                TextButton(
                    enabled = categoryName.isNotBlank(),
                    onClick = {

                        viewModel.addCategory(categoryName.trim())
                        categoryName = ""
                        showAddDialog = false
                    }
                ) {

                    Text(
                        text = "افزودن",
                        fontFamily = VazirFontFamily
                    )
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showAddDialog = false
                    }
                ) {

                    Text(
                        text = "لغو",
                        fontFamily = VazirFontFamily
                    )
                }
            }
        )
    }
    if (showEditDialog) {

        AlertDialog(
            onDismissRequest = {
                showEditDialog = false
                categoryToEdit = null
                categoryName = ""
            },

            title = {
                Text(
                    text = "ویرایش دسته‌بندی",
                    fontFamily = VazirFontFamily
                )
            },

            text = {
                OutlinedTextField(
                    value = categoryName,
                    onValueChange = {
                        categoryName = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text(
                            text = "نام دسته‌بندی",
                            fontFamily = VazirFontFamily
                        )
                    },
                    shape = RoundedCornerShape(
                        Dimens.CornerBottomSheet
                    )
                )
            },

            confirmButton = {

                TextButton(
                    enabled = categoryName.isNotBlank(),
                    onClick = {

                        categoryToEdit?.let { category ->

                            viewModel.updateCategory(
                                category.copy(
                                    name = categoryName.trim()
                                )
                            )
                        }

                        categoryName = ""
                        categoryToEdit = null
                        showEditDialog = false
                    }
                ) {
                    Text(
                        text = "ذخیره",
                        fontFamily = VazirFontFamily
                    )
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showEditDialog = false
                        categoryToEdit = null
                        categoryName = ""
                    }
                ) {
                    Text(
                        text = "لغو",
                        fontFamily = VazirFontFamily
                    )
                }
            }
        )
    }
}

/**
 * Single category row item.
 *
 * Displays category information and actions.
 * Default categories only show the default badge.
 * Custom categories can be edited or deleted.
 */
@Composable
private fun CategoryItem(
    category: Category,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = category.name,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = VazirFontFamily
            )

            if (category.isDefault) {

                Text(
                    text = "پیش‌فرض",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = VazirFontFamily
                )

            } else {

                IconButton(
                    onClick = onEdit
                ) {

                    Icon(
                        imageVector = PhosphorIcons.Duotone.PencilSimple,
                        contentDescription = "ویرایش",
                        tint = MaterialTheme.colorScheme.primary

                    )
                }

                IconButton(
                    onClick = onDelete
                ) {

                    Icon(
                        imageVector = PhosphorIcons.Duotone.Trash,
                        contentDescription = "حذف",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}