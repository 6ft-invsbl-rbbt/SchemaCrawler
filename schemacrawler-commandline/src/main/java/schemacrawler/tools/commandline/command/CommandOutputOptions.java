/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2019, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/

package schemacrawler.tools.commandline.command;


import static sf.util.Utility.isBlank;

import java.nio.file.Path;
import java.util.Optional;

import picocli.CommandLine;

/**
 * Parses the command-line.
 *
 * @author Sualeh Fatehi
 */
public final class CommandOutputOptions
{

  @CommandLine.Option(names = {
    "-o", "--output-file"
  },
                      description = {
                        "Generate output in a named file",
                        "<outputfile> is the path to the output file",
                        "Optional, defaults to the console (stdout) for text output, "
                        + "and a random file in the current directory for binary output"
                      })
  private Path outputFile;
  @CommandLine.Option(names = {
    "--output-format"
  },
                      description = {
                        "Format of the SchemaCrawler output, where <outputformat> is one of:",
                        "  text - For text output (default)",
                        "  html - For HTML5 output",
                        "  json - For JavaScript Object Notation output",
                        "  scdot - For Graphviz DOT output, for schema only",
                        "",
                        "You can generate a database diagram using Graphviz",
                        "For a diagram <outputformat> is one of dot, eps, gif, jpg, or png",
                        "For a complete list Graphviz output formats, see http://www.graphviz.org/",
                        "You can generate HTML output format with an embedded SVG diagram "
                        + "with an <outputformat> of htmlx",
                        "IMPORTANT: Graphviz needs to be installed, and available on the system PATH",
                        "http://www.graphviz.org/",
                        "If Graphviz is not installed, a DOT file is produced.",
                        "",
                        "Optional, defaults to the format specified by the output file, "
                        + "otherwise, text"
                      })
  private String outputFormatValue;
  @CommandLine.Option(names = {
    "-m", "--title"
  },
                      description = {
                        "Shows the title on the output",
                        "Optional, defaults to no title being shown"
                      })
  private String title;

  public Optional<String> getTitle()
  {
    if (isBlank(title))
    {
      return Optional.empty();
    }
    else
    {
      return Optional.of(title);
    }
  }

  public Optional<Path> getOutputFile()
  {
    if (outputFile == null)
    {
      return Optional.empty();
    }
    else
    {
      return Optional.of(outputFile.normalize().toAbsolutePath());
    }
  }

  public Optional<String> getOutputFormatValue()
  {
    if (isBlank(outputFormatValue))
    {
      return Optional.empty();
    }
    else
    {
      return Optional.of(outputFormatValue);
    }
  }

}