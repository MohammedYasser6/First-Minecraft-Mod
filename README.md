# 💎 Blue Sapphire Mod (FirstMod)

A Fabric Minecraft mod that introduces a new resource ecosystem based on **Blue Sapphire**. This mod adds a variety of building blocks, a new tier of tools, unique utility items, and food, all seamlessly integrated into the game.

> **Technical Note:** This project utilizes **Fabric Data Generation** to automatically generate Recipes, Loot Tables, Block Models, Item Models, and Tags, ensuring cleaner code and easier maintenance.

---

## ✨ Features

### 🌍 World Gen & Resources
* **Blue Sapphire Ore:** Generates in the world (available in both Stone and Deepslate variants).
* **Raw Blue Sapphire:** Dropped when mining ores without Silk Touch.
* **Blue Sapphire Gem:** Obtained by smelting or blasting Raw Blue Sapphire.

### ⚔️ Tools & Equipment
A completely new tool tier balanced to sit comfortably between Iron and Diamond, with high magic potential.
* **Stats:**
    * **Durability:** 850 (Significantly higher than Iron).
    * **Mining Speed:** 7.0 (Faster than Iron).
    * **Enchantability:** 18 (High enchantability, similar to Gold).
    * **Mining Tier:** Iron Level (Can mine Diamond, Redstone, etc.).
* **Available Tools:** Sword, Pickaxe, Axe, Shovel, and Hoe.

### 🏗️ Building Blocks
A full suite of construction blocks with a distinct blue aesthetic. All blocks include proper collision, connection states, and drops.
* **Base Blocks:** Block of Blue Sapphire, Raw Blue Sapphire Block.
* **Shapes:** Stairs, Slabs, Walls.
* **Fencing:** Fences, Fence Gates.
* **Redstone Inputs:** Buttons, Pressure Plates.
* **Entrances:** Doors, Trapdoors.

### 💡 Functional Items
* **Blue Sapphire Lamp:** A clickable lamp that toggles ON/OFF when right-clicked.
* **Chisel:** A new utility tool with custom durability.
* **Beef Sandwich:** A food item that provides a substantial hunger refill.

---

## 📜 Recipes

| Item | Ingredients | Pattern |
| :--- | :--- | :--- |
| **Blue Sapphire Lamp** | 1 Redstone, 8 Blue Sapphires | Redstone in center, Sapphires surrounding |
| **Beef Sandwich** | 2 Bread, 1 Cooked Beef | Bread (Top), Beef (Middle), Bread (Bottom) |
| **Chisel** | 1 Stick, 1 Iron Ingot, 1 Sapphire | Diagonal pattern |
| **Tools** | Blue Sapphires + Sticks | Standard Vanilla Patterns |
| **Building Blocks** | Blue Sapphire Block | Standard Vanilla Patterns (Stonemason/Crafting) |

---

## 🛠️ Technical Overview

This mod demonstrates advanced Fabric development techniques:
* **DataGen:** No manual JSON files. All data is generated via Java providers (`ModRecipeProvider`, `ModModelProvider`, `ModLootTableProvider`, etc.).
* **Custom Tags:** Implements custom tags for tool tiers (`incorrect_for_blue_sapphire_tool`).
* **Interactive Blocks:** Custom block classes for the Lamp's click functionality.

---

## 📦 Installation

1.  Install **Minecraft Java Edition**.
2.  Install the **Fabric Loader**.
3.  Download the **Fabric API** mod.
4.  Drop the `firstmod.jar` and Fabric API into your `.minecraft/mods` folder.

---

## 👤 Author

Developed by **Mohammed**.
*Built for Fabric 1.21+*
