import os
import bpy

# path_to_x3d should be the folder where all your sub directories are for your .x3d files
# main_dir is the folder where the sub directory folders that actually contain your x3d files are.
# path_to_stlconverted is the folder that will contain all the .stl files.

# Example (Windows path): path_to_x3d = os.path.join('C:\\users\\Dom\\navisudata', 'x3d2stl')
# Make sure your Main_dir has "C:\\.." this needs two "\" or it won't work.
# if you have divided your files into seperate sub directories, you will need to import each folder seperately. 
# Ex: 'C:\\users\\Dom\\Main Folder', 'Folder 1') - Import then,'C:\\users\\Dom\\Main Folder', 'Folder 2')

path_to_x3d = os.path.join('/home/dom/navisudata', 'x3d')
path_to_stlconverted = os.path.join('/home/dom/navisudata', 'stl')
print (path_to_x3d)
# Creating list of .x3d and only .x3d files
file_list = sorted(os.listdir(path_to_x3d))
x3d_list = [item for item in file_list if item.endswith('.x3d')]

for item in x3d_list:
   path_to_files = os.path.join(path_to_x3d, item)
   bpy.ops.import_scene.x3d(filepath = path_to_files)

# this remove .x3d at the end of the filename before saving
# Beware ! it erases all the characters ., x, 3 and d from the name of the file
# Xanadoo_3rd_film_of_Orson_Welles.x3d becomes anaoo_r_film_of_Orson_Welles ...
   namestl = item.translate({ord(i):None for i in '.x3d'})
   namestl = namestl + '.stl'

# saving file
   path_to_exportfiles = os.path.join(path_to_stlconverted, namestl)
   bpy.ops.export_mesh.stl(filepath = path_to_exportfiles)

